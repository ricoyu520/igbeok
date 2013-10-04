package com.igbeok.ws.orderservice;

import java.io.Serializable;
import java.util.Random;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	public String validate() {
		Random random = new Random();
		String resultsStrings[] = { "pass", "fail" };
		return resultsStrings[random.nextInt(2)];
	}

}
