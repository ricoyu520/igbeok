package com.igbeok.hibernate.common.orm.hibernate.convertor;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类型转换器的抽象类, 所有子类的转换方法都需要声明为CONVERT_METHOD所定义的方法名,参数类型任意
 * 
 * @author ricoyu
 * 
 */
public abstract class TypeConvertor {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private static final String CONVERT_METHOD = "convert2Target";

	/**
	 * 将value转换成requiredType类型.如果转换成功, 返回转换后的值,
	 * 如果该TypeConvertor不适用于当前的(value,requiredType), 则返回null
	 * 
	 * @param <T>
	 * @param value
	 * @param requiredType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final <T> T convert(Object value, Class<T> requiredType) {
		Class<?>[] parameterTypes = new Class<?>[] { value.getClass(), requiredType };
		Method method;
		try {
			//这里是为了找到子类中符合这两个参数类型(value.getClass(), requiredType)的方法
			method = getClass().getDeclaredMethod(CONVERT_METHOD, parameterTypes);
			method.setAccessible(true);
			//调用这个方法
			return (T) method.invoke(this, new Object[] { value, null });
		} catch (NoSuchMethodException e) {
			/*if (log.isWarnEnabled()) {
				log.info("Convert from " + value.getClass().getName() + " to " + requiredType
						+ " is not appropriate for " + getClass().getName());
			}*/
			//这边log不要了, 否则老是出现一堆warn
		} catch (Throwable e) {
			log.warn("DateTypeConvertor error", e);
		}
		return null;
	}
}
