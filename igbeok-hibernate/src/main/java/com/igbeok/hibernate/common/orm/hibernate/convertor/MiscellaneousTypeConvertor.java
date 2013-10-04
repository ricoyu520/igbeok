package com.igbeok.hibernate.common.orm.hibernate.convertor;

/**
 * 不太好分类的TypeConvertor都在这里。
 * @author rico.yu
 *
 */
public class MiscellaneousTypeConvertor extends TypeConvertor{

	/**
	 * 将Character类型转换成String
	 * @param value
	 * @param target
	 * @return
	 */
	private Object convert2Target(Character value, String target) {
		return value.toString();
	}
}
