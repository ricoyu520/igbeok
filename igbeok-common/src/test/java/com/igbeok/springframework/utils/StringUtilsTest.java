package com.igbeok.springframework.utils;

import org.junit.Test;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.util.StringUtils;

public class StringUtilsTest {

	@Test
	public void testTokenizeToStringArray(){
		String names = "name1, name2, name3; name4, name5; name6; name7; name8;name9";
		String[] nameArr = StringUtils.tokenizeToStringArray(names, BeanDefinitionParserDelegate.MULTI_VALUE_ATTRIBUTE_DELIMITERS);
		for(String name : nameArr) {
			System.out.print(name + " ");
		}
	}
}
