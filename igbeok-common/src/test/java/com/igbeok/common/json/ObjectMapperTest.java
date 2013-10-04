package com.igbeok.common.json;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperTest {

	@Test
	public void readJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Person me = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", Person.class);
			System.out.println(me.getName() + " " + me.getAge());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
