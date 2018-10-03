package com.telefonica.portalmiddleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class BaseController {
	@Autowired
	private static ApplicationContext context;
	public static ApplicationContext getContext(){
		return context;
	}

}
