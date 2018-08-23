package com.telefonica.portalmiddleware.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.telefonica.portalmiddleware.service.EventResponsysService;
import com.telefonica.portalmiddleware.service.MemberResponsysService;
import com.telefonica.portalmiddleware.service.TokenResponsysService;

@Controller
public class MiddleController extends BaseController {
	private String tokenResponsys;
	private String endpointResponsys;
	@Autowired
	private TokenResponsysService tokenResponsysService;
	@Autowired
	private EventResponsysService eventResponsysService;
	@Autowired
	private MemberResponsysService memberResponsysService;
	private final Logger LOG = LogManager.getLogger(getClass());
	@Scheduled(initialDelay=5000, fixedDelayString = "${schedule.fixedDelayString}")
	public void renewTokenResponsys(){
		LOG.trace("MiddleController debug");
		LOG.debug("MiddleController debug");
		LOG.info("MiddleController info");
		LOG.error("MiddleController error");
		LOG.fatal("MiddleController fatal");
		try {
			JSONObject jsonObject=tokenResponsysService.service();
			tokenResponsys=jsonObject.getString("authToken");
			endpointResponsys=jsonObject.getString("endPoint");
			
			eventResponsysService.setEndPoint(endpointResponsys);
			eventResponsysService.getHeaders().put("Authorization", tokenResponsys);
			memberResponsysService.setEndPoint(endpointResponsys);
			memberResponsysService.getHeaders().put("Authorization", tokenResponsys);
		} catch (Throwable e) {
			LOG.error("Hubo un error al renovar el token de Responsys: ", e);
		}
	}

	
	
}
