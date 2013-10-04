package com.igbeok.i18n.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/i18n")
public class I18nController {

	@Value("${jdbc.h2.driver}")
	private String driverString;
	@Autowired
	private AbstractMessageSource messageSource;

	@RequestMapping("/message/{key}")
	@ResponseBody
	public String getMessage(@PathVariable("key") String key, Locale locale, HttpServletRequest request) {
		try {
			request.getHeader("Accept-Language");
			String value = messageSource.getMessage(key, null, locale);
			return value + "你好!";
		} catch (NoSuchMessageException e) {
			return "No message found under key:" + key;
		}
	}
}
