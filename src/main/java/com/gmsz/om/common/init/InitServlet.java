/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gm-sz.com 2014
 * FileName: InitServlet.java
 * Modify record:
 * NO. |     Date       |    Version      |    Name           |      Content
 * 1   | 2014年6月12日        |   $Revision$  |  GMSZ)LuHaosheng  | original version
 *******************************************************************************
 */
package com.gmsz.om.common.init;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gmsz.om.common.constant.StateDefine;

/**
 * Class name:InitServlet
 * Description: 系统初始化Servlet
 * @author LuHaosheng
 */
@WebServlet(name="InitServlet",urlPatterns={"/init"}, loadOnStartup=0, initParams={@WebInitParam(name="log4j", value="WEB-INF/log4j.properties")})
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -7323490722699849829L;
	
	private static final Logger sysLogger = Logger.getLogger(StateDefine.SYS_LOG);
	
	public static WebApplicationContext webApplicationContext;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String prefix = config.getServletContext().getRealPath("/");
		
		
		
		ServletContext context = config.getServletContext();
		InitServlet.webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		
		
//		
//		try {
//			new AlarmUdpServer().init();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// Log4J
		String log4jFile = config.getInitParameter("log4j");
		String log4jConfigPath = prefix + log4jFile;
		PropertyConfigurator.configure(log4jConfigPath);
		
		sysLogger.info("*** Start init: --Class: InitServlet  --Method: init");
		
		

	}

	@Override
	public void destroy() {
		sysLogger.info("*** Start destroy: --Class: InitServlet  --Method: destroy");
		super.destroy();
		
		try {
			sysLogger.info("*** Start deregister driver: --Class: InitServlet  --Method: destroy");
	        Enumeration<Driver> drivers = DriverManager.getDrivers();
	        while(drivers.hasMoreElements()) {
	            DriverManager.deregisterDriver(drivers.nextElement());
	        }
	        sysLogger.info("*** End deregister driver: --Class: InitServlet  --Method: destroy");
	    } catch(Throwable ex) {
	    	sysLogger.error("*** Error occurred: ", ex);
	    }
		
		ProxoolFacade.shutdown();
		sysLogger.info("*** End destroy: --Class: InitServlet  --Method: destroy");
	}
}
