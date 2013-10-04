package com.igbeok.hibernate.common.orm.hibernate.convertor;

import java.math.BigDecimal;


public class NumberTypeConvertor extends TypeConvertor {

	private Object convert2Target(BigDecimal value, Long target) {
		return value.longValue();
	}
	
	private Object convert2Target(BigDecimal value, Float target) {
		return value.floatValue();
	}
	
	private Object convert2Target(BigDecimal value, Integer target) {
		return value.intValue();
	}
	
	private Object convert2Target(BigDecimal value, int target) {
		return value.intValue();
	}
	
	private Object convert2Target(BigDecimal value, long target) {
		return value.longValue();
	}
	
	private Object convert2Target(BigDecimal value, float target) {
		return value.floatValue();
	}
	
	private Object convert2Target(BigDecimal value, double target) {
		return value.doubleValue();
	}
}
