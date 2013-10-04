package com.igbeok.hibernate.common.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.igbeok.hibernate.common.util.clazz.ClassUtil;

/**
 * 该类通过一个指定的<code>ClassLoader</code>或者一个<code>Class</code>对象来加载Resource
 * 
 * @author ricoyu
 * 
 */
public class ClassPathResource implements Resource {
	private final String path;

	private ClassLoader classLoader;

	private Class clazz;

	public ClassPathResource(String path) {
		this(path, (ClassLoader)null);
	}

	public ClassPathResource(String path, ClassLoader classLoader) {
		if (StringUtils.isEmpty(path))
			throw new IllegalArgumentException("Path can not be empty");

		if (path.startsWith("/"))
			path = path.substring(1);

		this.path = path;
		this.classLoader = classLoader != null ? classLoader : ClassUtil.getDefaultClassLoader();
	}

	public ClassPathResource(String path, Class clazz) {
		if (StringUtils.isEmpty(path))
			throw new IllegalArgumentException("Path can not be empty");
		this.path = path;
		this.clazz = clazz;
	}

	public InputStream getInputStream() throws IOException {
		InputStream is = null;
		if(this.clazz!=null){
			is = this.clazz.getResourceAsStream(this.path);
		}else if(this.classLoader!=null){
			is = classLoader.getResourceAsStream(this.path);
		}
		if(is == null){
			throw new FileNotFoundException(getDescription()+" not found");
		}
		return is;
	}

	public final ClassLoader getClassLoader() {
		return (this.classLoader != null ? this.classLoader : this.clazz.getClassLoader());
	}
	
	public String getDescription() {
		return "class path resource [" + this.path + "]";
	}

}
