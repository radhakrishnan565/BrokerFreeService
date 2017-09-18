package com.rs.server.loader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartUpListenner implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Server Startup listner executed....");
		BootStrapLoader loader = new BootStrapLoader();
		loader.load();
		
	}

}
