package com.igbeok.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/rest")
public class RestController {

	private RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("/message")
	public String getMessage(){
		String valueString = restTemplate.getForObject("http://localhost:8080/showcase/i18n/message?key=user.name", String.class);
		return valueString;
	}
}
