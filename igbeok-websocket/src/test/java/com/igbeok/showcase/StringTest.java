package com.igbeok.showcase;

import org.junit.Test;

public class StringTest {

	@Test
	public void testStringEqual() {
		String aString = "abc";
		String b = new String("abc");
		System.out.println(aString == b);
	}

	@Test
	public void testStringEqual2() {
		String a = "ab";
		String b = "a" + "b";
		
		System.out.println(a == b);
	}
}
