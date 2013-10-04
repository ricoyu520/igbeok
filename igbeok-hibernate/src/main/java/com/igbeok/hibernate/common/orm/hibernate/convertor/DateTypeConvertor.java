package com.igbeok.hibernate.common.orm.hibernate.convertor;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 日期类型的转换器,所有日期类型的转换都发生在这里
 * 
 * @author ricoyu
 * 
 */
public class DateTypeConvertor extends TypeConvertor {

	private Object convert2Target(Date value, Timestamp target) {
		return new Timestamp(value.getTime());
	}

	private Object convert2Target(Timestamp value, Date target) {
		return new Date(value.getTime());
	}

}
