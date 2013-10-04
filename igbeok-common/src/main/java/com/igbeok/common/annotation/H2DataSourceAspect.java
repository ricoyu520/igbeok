package com.igbeok.common.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class H2DataSourceAspect {

	private Log log = LogFactory.getLog(H2DataSourceAspect.class);
	
	@Pointcut("@annotation com.igbeok.common.annotation.H2DataSourceAspect")
	public void doPointCut(){}
	
	@Before("@annotation com.igbeok.common.annotation.H2DataSourceAspect")
	public void doBefore(JoinPoint joinPoint) {
		log.debug("Using H2 datasource for " + joinPoint);
		//DataSourceSelector.setDataSource(DataSourceSelector.H2);
	}
	
	public void doAfter(JoinPoint joinPoint) {
		log.debug("Reverting datasource after " + joinPoint);
		//DataSourceSelector.clearDataSource();
	}
}
