package com.igbeok.common.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFactoryTest {

	public JsonFactoryTest createFactory(){
		JsonFactory jf = new JsonFactory();
		
		ObjectMapper om = new ObjectMapper();
		JsonFactory jf2 = om.getFactory();
		
		return null;
	}
}
