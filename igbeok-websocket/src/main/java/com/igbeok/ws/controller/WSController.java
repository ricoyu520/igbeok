package com.igbeok.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igbeok.ws.HelloWorld;

@Controller
@RequestMapping("/ws")
public class WSController {

	@Autowired
	private HelloWorld helloClient;

	@RequestMapping("/say-hello/{message}")
	@ResponseBody
	public String helloWS(@PathVariable("message") String message) {
		String responseMessageString = helloClient.sayHi(message);
		return responseMessageString;
	}
}
