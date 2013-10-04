package com.igbeok.hibernate.common.util.properties;


/**
 * 当属性文件中的配置信息如下时,自动将后面省略包名的类看作是与前一个类在同一个包下,返回添加包名后的value值
 * hibernate.type_convertor=org.rico.common.orm.hibernate.convertor.DateTypeConvertor, NumberTypeConvertor
 * 
 * 注意,第一个类必须包含类名
 * @author ricoyu
 *
 */
public class PackageAutoDetactPropertiesResolver extends PropertiesResolverDecorator {
	private static final String DOT = ".";
	private static final String COMMA = ",";
	
	public PackageAutoDetactPropertiesResolver(PropertiesResolver propertiesResolver){
		super(propertiesResolver);
	}

	/**
	 * 返回以逗号分隔的完全限定类名
	 */
	public String getProperty(String key) {
		String value = propertiesResolver.getProperty(key);
		if (null == value)
			return value;
		String[] values = value.split(COMMA);
		if (values.length == 1) {
			return value;
		} else {
			StringBuilder finalValue = new StringBuilder();
			// StringBuilder combinedValue = new StringBuilder();
			String packageName = "";
			for (int i = 0; i < values.length; i++) {
				String temp = values[i].trim();
				int dotIndex = temp.lastIndexOf(DOT);
				// 该类名包含点号就表示这个是一个完全限定类名,分离出包名
				if (dotIndex != -1) {
					packageName = temp.substring(0, dotIndex);
					// 将完全限定类名加入最后结果集中
					finalValue.append(temp + COMMA);
				} else {
					finalValue.append(packageName + DOT + temp + COMMA);
				}
			}
			return finalValue.deleteCharAt(finalValue.length() - 1).toString();
		}
	}

}
