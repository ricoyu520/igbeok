package com.igbeok.hibernate.common.util.properties;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.igbeok.hibernate.common.io.Resource;
import com.igbeok.hibernate.common.util.properties.exception.LoadResourceException;

public class NormalPropertiesResolver extends AbstractPropertiesResolver {

	/**
	 * 根据提供的属性文件名加载,如果不提供属性文件名,加载ClassPath下的所有属性文件
	 * 
	 * @param propertyFileName
	 */
	/*public NormalPropertiesResolver(String propertyFileName, Resource resource) {
		super(propertyFileName, resource);
	}
*/
	public NormalPropertiesResolver(Resource resource) {
		super(resource);
	}

	/**
	 * 根据属性key获取属性值
	 */
	public String getProperty(String key) {
		if (StringUtils.isBlank(key))
			throw new IllegalArgumentException("key can not be empty!");

		try {
			properties.load(resource.getInputStream());
		} catch (IOException e) {
			throw new LoadResourceException("Load properties file failed", e);
		}

		String value = properties.getProperty(key);
		if (StringUtils.isBlank(value))
			return null;
		return value;
	}

}
