package com.light.listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerTest implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("监听器初始化...");
	}

}
