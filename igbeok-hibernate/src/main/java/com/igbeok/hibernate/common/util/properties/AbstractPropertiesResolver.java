package com.igbeok.hibernate.common.util.properties;

import java.util.Properties;

import com.igbeok.hibernate.common.io.Resource;

public abstract class AbstractPropertiesResolver implements PropertiesResolver {

//	protected String propertyFileName;
	protected Resource resource;
	protected Properties properties = new Properties();
	
	
	/**
	 * 根据提供的属性文件名加载,如果不提供属性文件名,加载ClassPath下的所有属性文件
	 * 
	 * @param propertyFileName
	 */
	/*public AbstractPropertiesResolver(String propertyFileName, Resource resource) {
		this.propertyFileName = StringUtils.isBlank(propertyFileName) ? "" : propertyFileName;
		if (resource == null)
			throw new IllegalArgumentException("Resource can not be null!");
		this.resource = resource;
	}*/

	public AbstractPropertiesResolver(Resource resource) {
//		this(null, resource);
		this.resource = resource;
	}

}
