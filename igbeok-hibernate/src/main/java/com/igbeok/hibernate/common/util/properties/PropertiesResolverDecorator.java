package com.igbeok.hibernate.common.util.properties;

public abstract class PropertiesResolverDecorator implements PropertiesResolver {

	protected PropertiesResolver propertiesResolver;
	
	public PropertiesResolverDecorator(PropertiesResolver propertiesResolver){
		if(propertiesResolver==null)
			throw new IllegalArgumentException("PropertiesResolver can not be null!");
		this.propertiesResolver = propertiesResolver;
	}

}
