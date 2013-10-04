package com.igbeok.placeholder.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/placeholder")
public class PlaceholderController {

	@Value("${jdbc.username}")
	private String userName;
	@Value("${maven.resource.plugin.placehold.test}")
	private String mavenResourcePlugin;

	@PostConstruct
	public void printProperties() {
		System.out.println("-----------mavenResourcePlugin----------------"+mavenResourcePlugin);
	}

	@RequestMapping("/value")
	@ResponseBody
	public String placeholder() {
		return userName;
	}

	@RequestMapping("/maven/resource")
	@ResponseBody
	public String mavenPaceholder() {
		return userName;
	}
}
