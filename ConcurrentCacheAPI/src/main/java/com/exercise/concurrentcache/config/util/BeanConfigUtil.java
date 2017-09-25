package com.exercise.concurrentcache.config.util;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.exercise.concurrentcache.constants.CacheConstants;

public class BeanConfigUtil {

	public static Object getBean(String bean) {
		FileSystemXmlApplicationContext applicationContext = null;
		try {
			applicationContext = new FileSystemXmlApplicationContext(CacheConstants.BEANS_XML);
			return applicationContext.getBean("manager");
		} finally {
			applicationContext.close();
		}
	}

}
