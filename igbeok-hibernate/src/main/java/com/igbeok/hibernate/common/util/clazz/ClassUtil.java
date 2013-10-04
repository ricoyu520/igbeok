package com.igbeok.hibernate.common.util.clazz;

public class ClassUtil {

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		cl = Thread.currentThread().getContextClassLoader();
		if (cl == null)
			cl = ClassUtil.class.getClassLoader();
		return cl;
	}
}
