package com.igbeok.common.exception;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex {
	private static String MESSAGE_REPLACE_TEMPLATE = "\\{(.+?)\\}";
	private static Pattern MESSAGE_REPLACE_PATTERN = Pattern.compile(MESSAGE_REPLACE_TEMPLATE);

	public static void main(String[] args) {
		Throwable ex = new Exception("hi {name} !");
		String message = ex.getLocalizedMessage();
		Matcher matcher = MESSAGE_REPLACE_PATTERN.matcher(message);
		if (matcher.find()) {
			String messageKeyString = matcher.group(1);
			StringBuffer bf = new StringBuffer();
			matcher.appendReplacement(bf, "rico");
			matcher.appendTail(bf);
			System.out.println(bf);
			System.out.println(ex.getLocalizedMessage());
			System.out.println(ex.getMessage());
			Class exTypeClass = ex.getClass();
			try {
				while(!(exTypeClass == Throwable.class)) {
					exTypeClass = exTypeClass.getSuperclass();
				}
				Field detailMessage = exTypeClass.getDeclaredField("detailMessage");
				detailMessage.setAccessible(true);
				try {
					detailMessage.set(ex, "today is subshine!");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("====================");
		System.out.println(ex.getMessage());
		System.out.println(ex.getLocalizedMessage());
	}
}
