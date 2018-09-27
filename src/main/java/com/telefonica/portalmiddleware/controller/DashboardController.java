package com.telefonica.portalmiddleware.controller;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController extends BaseController{
	@Autowired
	@Qualifier("propertyConfigurer")
	private Properties portalMiddlewareProperties;
	private final Logger LOG = LogManager.getLogger(getClass());
	
	@RequestMapping("/dashboard")
	public ModelAndView showDashboard() {
		LOG.debug("showDashboard INICIO log4j");
		System.out.println("showDashboard INICIO nativo");
		return new ModelAndView("dashboard", "properties", portalMiddlewareProperties);
	}

}
