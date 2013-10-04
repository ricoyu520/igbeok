package com.igbeok.hibernate.common.orm.hibernate.transform;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

import com.igbeok.hibernate.common.orm.hibernate.property.CustomPropertyAccessorFactory;
import com.igbeok.hibernate.common.orm.hibernate.transform.convertor.ColumnNameConvertor;

/**
 * ResultTransformer的抽象类,提供了数据库字段名到javabean属性名的转换
 * 每个子类都需要实现getConvertorInstance()方法,以提供一个字段-属性名称转换器
 * 
 * @author ricoyu
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractFieldNameToBeanResultTransformer implements ResultTransformer {

	private final Class<?> resultClass;
	private final PropertyAccessor propertyAccessor;
	private Setter[] setters;
	private ColumnNameConvertor convertor;

	public AbstractFieldNameToBeanResultTransformer(Class<?> resultClass) {
		if (resultClass == null) {
			throw new IllegalArgumentException("resultClass cannot be null");
		}
		this.resultClass = resultClass;
		// convertor = new UnderlineStyleColumnNameConvertor();
		propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] { CustomPropertyAccessorFactory
				.getPropertyAccessor(CustomPropertyAccessorFactory.AUTO) });
	}

	/**
	 * 数组tuple中存放的是具体的值, aliases数组中存放的是列名
	 * 如果sql查询返回10条记录, 那么这个方法将被调用10次, 每次传入一行记录的值: tuple, 一行列名aliases
	 * 
	 * transformTuple方法是一个模板方法,getConvertorInstance()是一个抽象方法, 需要之类去实现.
	 */
	public final Object transformTuple(Object[] tuple, String[] aliases) {
		Object result;

		try {
			if (setters == null) {
				setters = new Setter[aliases.length];
				for (int i = 0; i < aliases.length; i++) {
					//这边将表的列名转换成bean属性名
					String alias = getConvertorInstance().toPropertyName(aliases[i]);
					if (alias != null) {
						setters[i] = propertyAccessor.getSetter(resultClass, alias);
					}
				}
			}
			result = resultClass.newInstance();

			for (int i = 0; i < aliases.length; i++) {
				if (setters[i] != null) {
					//在这边调用bean相应属性的setter, 给属性设置值,有需要的数据类型转换也发生在这里
					//这边setters里面存放的是自定义的PropertyAccessor: AutoConvertPropertyAccessor里面实现的Setter
					
					setters[i].set(result, tuple[i], null);
				}
			}
		} catch (InstantiationException e) {
			throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
		} catch (IllegalAccessException e) {
			throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
		}

		return result;
	}

	public List transformList(List collection) {
		return collection;
	}

	public int hashCode() {
		int result;
		result = resultClass.hashCode();
		result = 31 * result + propertyAccessor.hashCode();
		return result;
	}


	/**
	 * 需要子类实现该方法,提供一种类型的Convertor
	 * 
	 * @return
	 */
	protected abstract ColumnNameConvertor getConvertorInstance();

}
