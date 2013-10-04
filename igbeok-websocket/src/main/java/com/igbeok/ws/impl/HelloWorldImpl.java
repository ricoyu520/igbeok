package com.igbeok.ws.impl;

import javax.jws.WebService;

import com.igbeok.ws.HelloWorld;

@WebService(endpointInterface = "com.igbeok.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHi(String text) {
		return "This is first Hello World Web Service!" + text;
	}

}
