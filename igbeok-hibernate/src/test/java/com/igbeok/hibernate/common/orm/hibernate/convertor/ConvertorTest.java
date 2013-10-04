package com.igbeok.hibernate.common.orm.hibernate.convertor;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Test;

public class ConvertorTest {
	@Test
	public void testConvert(){
		DateTypeConvertor convertor = new DateTypeConvertor();
		Date value = new Date(new java.util.Date().getTime());
		System.out.println(convertor.convert(value, Timestamp.class));
	}
}
