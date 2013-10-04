package com.igbeok.hibernate.common.util.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igbeok.hibernate.common.orm.hibernate.convertor.TypeConvertor;
import com.igbeok.hibernate.common.orm.hibernate.convertor.factory.ConvertFactory;

/**
 * 进行类型转换的帮助类
 * 
 * @author ricoyu
 * 
 */
public class TypeConvertUtil {

	private static final String CONVERTOR_KEY_NAME = "hibernate.type_convertor";
	Logger log = LoggerFactory.getLogger(TypeConvertUtil.class);

	private static volatile TypeConvertUtil instance;
	//这里读取属性文件中配置的convertor
	private TypeConvertor[] convertors = ConvertFactory.getConfiguredTypeConvertors(CONVERTOR_KEY_NAME);

	private TypeConvertUtil() {
	};

	public static TypeConvertUtil getInstance() {
		if (null == instance) {
			synchronized (TypeConvertUtil.class) {
				if (null == instance) {
					instance = new TypeConvertUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * 遍历TypeConvertor数组,尝试通过这些TypeConvertor将value转换成目标类型
	 * 
	 * @param value
	 * @param requiredType
	 * @return Object
	 */
	public final Object convert(Object value, Class<?> requiredType) {
		Object convertedValue = null;
		for (TypeConvertor convertor : convertors) {
			convertedValue = convertor.convert(value, requiredType);
			if (convertedValue != null)
				break;
		}
		return convertedValue == null ? value : convertedValue;
	}
}
