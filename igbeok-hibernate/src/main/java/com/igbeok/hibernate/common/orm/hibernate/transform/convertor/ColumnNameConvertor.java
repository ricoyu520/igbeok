package com.igbeok.hibernate.common.orm.hibernate.transform.convertor;

/**
 * 数据库字段命名应该有一套规则， 比如PERSON_ID, USER_NAME等以下划线隔开的风格。 或者name，
 * content等仅包含一个单词的column名字。 通过实现这个接口， 提供不同的column名到字段名的转换规则
 * 
 * @author ricoyu
 * 
 */
public interface ColumnNameConvertor {

	/**
	 * 将相应的数据库字段名转换成javabean的property名字
	 * 
	 * @param columnName
	 * @return
	 */
	String toPropertyName(String columnName);
}
