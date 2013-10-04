package com.igbeok.common.obj;

import org.junit.Test;

public class ObjectTest {

	@Test
	public void getClassTest() {
		Number number = 0;
		Class<? extends Number> cls = number.getClass();
		System.out.println(cls.getName());
	}
}
