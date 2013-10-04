package com.igbeok.ws;

import javax.xml.ws.Endpoint;

import com.igbeok.ws.impl.HelloWorldImpl;

public class WSPublisher {

	public static void main(String[] args) {
		System.out.println("Starting Server...");
		HelloWorld helloWorld = new HelloWorldImpl();
		String address = "http://localhost:9001/helloWorld";
		Endpoint.publish(address, helloWorld);
	}
}
