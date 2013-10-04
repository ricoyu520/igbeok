package com.igbeok.hibernate.common.orm.hibernate.property;

import org.hibernate.property.PropertyAccessor;

public final class CustomPropertyAccessorFactory {

	private static final AutoConvertPropertyAccessor AUTO_CONVERT_ACCESSOR = new AutoConvertPropertyAccessor();

	// PropertyAccessor types
	public static final String AUTO = "auto";

	public static PropertyAccessor getPropertyAccessor(String type) {
		if (AUTO.equalsIgnoreCase(type)) {
			return AUTO_CONVERT_ACCESSOR;
		}
		return null;
	}
}
