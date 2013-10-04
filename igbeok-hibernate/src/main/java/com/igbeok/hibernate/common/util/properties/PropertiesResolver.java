package com.igbeok.hibernate.common.util.properties;

public interface PropertiesResolver {

	/**
	 * 根据key获得相应的value值
	 * 
	 * @param key
	 * @return
	 */
	String getProperty(String key);
}
