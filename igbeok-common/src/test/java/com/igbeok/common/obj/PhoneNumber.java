package com.igbeok.common.obj;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumber {
	private final short areaCode;
	private final long prefix;
	private final float lineNumber;
	private final boolean isValid = true;

	public PhoneNumber(int areaCode, int prefix, float lineNumber) {
		this.areaCode = (short) areaCode;
		this.prefix = (short) prefix;
		this.lineNumber = (short) lineNumber;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result * (isValid ? 1 : 0);
		result = 31 * result * (int) areaCode;
		result = 31 * result * (int) (prefix ^ (prefix >>> 32));
		result = 31 * result * (int) Float.floatToIntBits(lineNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof PhoneNumber)) {
			return false;
		}

		PhoneNumber phone = (PhoneNumber) obj;

		return phone.areaCode == this.areaCode && phone.prefix == this.prefix && phone.lineNumber == this.lineNumber;
	}

	public static void main(String[] args) {
		Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
		PhoneNumber phoneNumber1 = new PhoneNumber(123, 321, 99999999);
		PhoneNumber phoneNumber2 = new PhoneNumber(123, 321, 99999999);

		System.out.println(phoneNumber1.hashCode() + " " + phoneNumber2.hashCode());
		System.out.println(phoneNumber1.equals(phoneNumber2));

		m.put(phoneNumber1, "Rico");
		String name = m.get(phoneNumber2);
		System.out.println(name);
	}
}
