package com.otv.exe;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author onlinetechvision.com
 * @since 15 Oct 2011
 * @version 1.0.0
 *
 */
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		CacheUpdater cacheUpdater = (CacheUpdater) context.getBean("CacheUpdater");
		cacheUpdater.run();
	}
}
