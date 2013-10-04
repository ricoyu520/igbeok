package com.igbeok.hibernate.common.orm.hibernate.convertor.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igbeok.hibernate.common.io.ClassPathResource;
import com.igbeok.hibernate.common.io.Resource;
import com.igbeok.hibernate.common.orm.hibernate.convertor.TypeConvertor;
import com.igbeok.hibernate.common.util.properties.NormalPropertiesResolver;
import com.igbeok.hibernate.common.util.properties.PackageAutoDetactPropertiesResolver;
import com.igbeok.hibernate.common.util.properties.PropertiesResolver;

/**
 * 这个类会读取属性文件中配置的convertor
 * @author rico
 *
 */
public final class ConvertFactory {
	private static final String COMMA = ",";
	private static final String PROPERTY_FILE = "extension.properties";
	private static final Logger log = LoggerFactory.getLogger(ConvertFactory.class);

	/**
	 * 根据配置文件中配置的TypeConvertor, 返回一个TypeConvertor的数组
	 * 
	 * @return
	 * @throws Exception
	 */
	public static TypeConvertor[] getConfiguredTypeConvertors(String keyName) {
		Resource resource = new ClassPathResource(PROPERTY_FILE);
		PropertiesResolver resolver = new NormalPropertiesResolver(resource);
		PropertiesResolver packageNameResolver = new PackageAutoDetactPropertiesResolver(resolver);
		String[] convertorNames = packageNameResolver.getProperty(keyName).split(COMMA);
		TypeConvertor[] convertors = new TypeConvertor[convertorNames.length];
		Class<TypeConvertor> clazz;
		try {
			for (int i = 0; i < convertorNames.length; i++) {
				clazz = (Class<TypeConvertor>) Class.forName(convertorNames[i]);
				convertors[i] = clazz.newInstance();
			}
		} catch (Exception e) {
			log.error("Get Configured TypeConvertors Error", e);
			throw new RuntimeException(e);
		}
		return convertors;
	}
}
