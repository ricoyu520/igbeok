package com.igbeok.hibernate.common.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {

	/**
	 * 每次调用该方法都应该返回一个全新的<code>InputStream</code>
	 * @return InputStream
	 * @throws IOException
	 */
	InputStream getInputStream() throws IOException;
}
