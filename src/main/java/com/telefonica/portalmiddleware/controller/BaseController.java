package com.telefonica.portalmiddleware.controller;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class BaseController {
	@Autowired
	@Qualifier("propertyConfigurer")
	protected Properties portalMiddlewareProperties;

	
}
