package com.igbeok.hibernate.common.io;

import java.io.IOException;

import org.junit.Test;

public class ResourceTest {

	@Test
	public void test(){
		ClassPathResource resource = new ClassPathResource("extension.properties");
		try {
			System.out.println(resource.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
