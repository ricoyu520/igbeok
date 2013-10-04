package com.igbeok.http.converter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	public static final MediaType DEFAULT_MEDIA_TYPE = new MediaType("text", "plain", DEFAULT_CHARSET);

	@Override
	protected MediaType getDefaultContentType(String t) throws IOException {
		return DEFAULT_MEDIA_TYPE;
	}

}
