package com.igbeok.hibernate.common.orm.hibernate.transform.convertor.impl;

import com.igbeok.hibernate.common.orm.hibernate.transform.convertor.ColumnNameConvertor;

/**
 * 将PERSON_ID这个风格的column name转换成相应的property name
 * 在2.0 版本中将这个类设为单例
 * 
 * @author ricoyu
 * @version 2.0
 * 
 */
public class UnderlineStyleColumnNameConvertor implements ColumnNameConvertor {
	private static final String SEPARATOR = "_";
	private static volatile UnderlineStyleColumnNameConvertor instance;
	
	public static UnderlineStyleColumnNameConvertor getInstance() {
		if (null == instance) {
			synchronized (UnderlineStyleColumnNameConvertor.class) {
				if (null == instance) {
					instance = new UnderlineStyleColumnNameConvertor();
				}
			}
		}
		return instance;
	}

	/**
	 * 该方法将column name转换成property name
	 */
	public String toPropertyName(String columnName) {
		String[] words = separateWords(columnName);
		return concatenateWords(words);
	}

	/**
	 * 如果就一个单词, 直接返回 如果有多个单词, 从第二个单词起,每个单词的首字母大写,其他小写
	 * 
	 * @param words
	 * @return
	 */
	private String concatenateWords(String[] words) {
		if (words.length == 1) {
			return words[0].toLowerCase();
		} else {
			StringBuilder propertyName = new StringBuilder(words[0].toLowerCase());
			for (int i = 1; i < words.length; i++) {
				String lowerCaseWord = words[i].toLowerCase();
				String firstLetter = String.valueOf(lowerCaseWord.charAt(0)).toUpperCase();
				String remainderLetters = null;
				if (lowerCaseWord.length() > 1) {
					remainderLetters = lowerCaseWord.substring(1);
				}
				propertyName.append(firstLetter).append(remainderLetters == null ? "" : remainderLetters);
			}
			return propertyName.toString();
		}
	}

	/**
	 * 将类似PERSON_ID这中名称转换成一个String数组,该数组内容为["PERSON","ID"]
	 * 
	 * @param columnName
	 * @return
	 */
	private String[] separateWords(String columnName) {
		if (null == columnName || columnName.isEmpty())
			throw new IllegalArgumentException("column name is null or empty!");

		String[] words;
		if (columnName.indexOf(SEPARATOR) != -1) {
			words = columnName.split(SEPARATOR);
		} else {
			words = new String[] { columnName };
		}
		return words;
	}
}
