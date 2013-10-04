package com.igbeok.hibernate.common.orm.hibernate.property;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.PropertyAccessException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.annotations.common.util.ReflectHelper;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igbeok.hibernate.common.util.convertor.TypeConvertUtil;

public class AutoConvertPropertyAccessor implements PropertyAccessor {
	private static final Logger log = LoggerFactory.getLogger(AutoConvertPropertyAccessor.class);

	public static final class AutoConvertSetter implements Setter {
		private static final long serialVersionUID = 1L;
		private Class<?> clazz;
		private final transient Method method;
		private final String propertyName;

		private AutoConvertSetter(Class<?> clazz, Method method, String propertyName) {
			this.clazz = clazz;
			this.method = method;
			this.propertyName = propertyName;
		}

		public Method getMethod() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getMethodName() {
			// TODO Auto-generated method stub
			return null;
		}

		public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException {
			try {
				// 神奇的代码发生在这里,convertToProperType将传进来的值转换成这个method所需要的数据类型
				method.invoke(target, new Object[] { convertToProperType(method, value) });
			} catch (NullPointerException npe) {
				if (value == null && method.getParameterTypes()[0].isPrimitive()) {
					throw new PropertyAccessException(npe, "Null value was assigned to a property of primitive type",
							true, clazz, propertyName);
				} else {
					throw new PropertyAccessException(npe, "NullPointerException occurred while calling", true, clazz,
							propertyName);
				}
			} catch (InvocationTargetException ite) {
				throw new PropertyAccessException(ite, "Exception occurred inside", true, clazz, propertyName);
			} catch (IllegalAccessException iae) {
				throw new PropertyAccessException(iae, "IllegalAccessException occurred while calling", true, clazz,
						propertyName);
				// cannot occur
			} catch (IllegalArgumentException iae) {
				log.error("Required type: " + method.getParameterTypes()[0].getName() + "; Actual type: "
						+ (value == null ? "null" : value.getClass().getName()));
				if (value == null && method.getParameterTypes()[0].isPrimitive()) {
					throw new PropertyAccessException(iae, "Null value was assigned to a property of primitive type",
							true, clazz, propertyName);
				} else {
					log.error("IllegalArgumentException in class: " + clazz.getName() + ", setter method of property: "
							+ propertyName);
					log.error("expected type: " + method.getParameterTypes()[0].getName() + ", actual value: "
							+ (value == null ? null : value.getClass().getName()));
					throw new PropertyAccessException(iae, "IllegalArgumentException occurred while calling", true,
							clazz, propertyName);
				}
			}
		}

	}

	public static final class AutoConvertGetter implements Getter {
		private Class clazz;
		private final transient Method method;
		private final String propertyName;

		private AutoConvertGetter(Class clazz, Method method, String propertyName) {
			this.clazz = clazz;
			this.method = method;
			this.propertyName = propertyName;
		}

		public Object get(Object target) throws HibernateException {
			try {
				return method.invoke(target, null);
			} catch (InvocationTargetException ite) {
				throw new PropertyAccessException(ite, "Exception occurred inside", false, clazz, propertyName);
			} catch (IllegalAccessException iae) {
				throw new PropertyAccessException(iae, "IllegalAccessException occurred while calling", false, clazz,
						propertyName);
				// cannot occur
			} catch (IllegalArgumentException iae) {
				log.error("IllegalArgumentException in class: " + clazz.getName() + ", getter method of property: "
						+ propertyName);
				throw new PropertyAccessException(iae, "IllegalArgumentException occurred calling", false, clazz,
						propertyName);
			}
		}

		public Object getForInsert(Object target, Map mergeMap, SessionImplementor session) {
			return get(target);
		}

		public Class getReturnType() {
			return method.getReturnType();
		}

		public Method getMethod() {
			return method;
		}

		public String getMethodName() {
			return method.getName();
		}

		public String toString() {
			return "BasicGetter(" + clazz.getName() + '.' + propertyName + ')';
		}

		Object readResolve() {
			return createGetter(clazz, propertyName);
		}

		public Member getMember() {
			return null;
		};
	}

	public Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException {
		return createGetter(theClass, propertyName);
	}

	/**
	 * 将value转换成该方法参数类型
	 * 
	 * @param method
	 * @param value
	 * @return
	 */
	private static Object convertToProperType(Method method, Object value) {
		if (value == null) {
			return value;
		}

		Class<?> requiredType = method.getParameterTypes()[0];

		// 如果value可以直接赋值给这个方法的参数类型,表示不需要类型转换
		if (requiredType.isAssignableFrom(value.getClass())) {
			return value;
		}

		return TypeConvertUtil.getInstance().convert(value, requiredType);
	}

	public Setter getSetter(Class theClass, String propertyName) throws PropertyNotFoundException {
		return createSetter(theClass, propertyName);
	}

	private static Setter createSetter(Class theClass, String propertyName) throws PropertyNotFoundException {
		AutoConvertSetter result = getSetterOrNull(theClass, propertyName);
		if (result == null) {
			throw new PropertyNotFoundException("Could not find a setter for property " + propertyName + " in class "
					+ theClass.getName());
		}
		return result;
	}

	private static AutoConvertSetter getSetterOrNull(Class theClass, String propertyName) {

		if (theClass == Object.class || theClass == null)
			return null;

		Method method = setterMethod(theClass, propertyName);

		if (method != null) {
			if (!ReflectHelper.isPublic(theClass, method))
				method.setAccessible(true);
			return new AutoConvertSetter(theClass, method, propertyName);
		} else {
			AutoConvertSetter setter = getSetterOrNull(theClass.getSuperclass(), propertyName);
			if (setter == null) {
				Class[] interfaces = theClass.getInterfaces();
				for (int i = 0; setter == null && i < interfaces.length; i++) {
					setter = getSetterOrNull(interfaces[i], propertyName);
				}
			}
			return setter;
		}

	}

	private static Method setterMethod(Class theClass, String propertyName) {

		AutoConvertGetter getter = getGetterOrNull(theClass, propertyName);
		Class returnType = (getter == null) ? null : getter.getReturnType();

		Method[] methods = theClass.getDeclaredMethods();
		Method potentialSetter = null;
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();

			if (methods[i].getParameterTypes().length == 1 && methodName.startsWith("set")) {
				String testStdMethod = Introspector.decapitalize(methodName.substring(3));
				String testOldMethod = methodName.substring(3);
				if (testStdMethod.equals(propertyName) || testOldMethod.equals(propertyName)) {
					potentialSetter = methods[i];
					if (returnType == null || methods[i].getParameterTypes()[0].equals(returnType)) {
						return potentialSetter;
					}
				}
			}
		}
		return potentialSetter;
	}

	public static Getter createGetter(Class theClass, String propertyName) throws PropertyNotFoundException {
		AutoConvertGetter result = getGetterOrNull(theClass, propertyName);
		if (result == null) {
			throw new PropertyNotFoundException("Could not find a getter for " + propertyName + " in class "
					+ theClass.getName());
		}
		return result;

	}

	private static AutoConvertGetter getGetterOrNull(Class theClass, String propertyName) {

		if (theClass == Object.class || theClass == null)
			return null;

		Method method = getterMethod(theClass, propertyName);

		if (method != null) {
			if (!ReflectHelper.isPublic(theClass, method))
				method.setAccessible(true);
			return new AutoConvertGetter(theClass, method, propertyName);
		} else {
			AutoConvertGetter getter = getGetterOrNull(theClass.getSuperclass(), propertyName);
			if (getter == null) {
				Class[] interfaces = theClass.getInterfaces();
				for (int i = 0; getter == null && i < interfaces.length; i++) {
					getter = getGetterOrNull(interfaces[i], propertyName);
				}
			}
			return getter;
		}
	}

	private static Method getterMethod(Class theClass, String propertyName) {

		Method[] methods = theClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			// only carry on if the method has no parameters
			if (methods[i].getParameterTypes().length == 0) {
				String methodName = methods[i].getName();

				// try "get"
				if (methodName.startsWith("get")) {
					String testStdMethod = Introspector.decapitalize(methodName.substring(3));
					String testOldMethod = methodName.substring(3);
					if (testStdMethod.equals(propertyName) || testOldMethod.equals(propertyName)) {
						return methods[i];
					}

				}

				// if not "get", then try "is"
				if (methodName.startsWith("is")) {
					String testStdMethod = Introspector.decapitalize(methodName.substring(2));
					String testOldMethod = methodName.substring(2);
					if (testStdMethod.equals(propertyName) || testOldMethod.equals(propertyName)) {
						return methods[i];
					}
				}
			}
		}
		return null;
	}
}
