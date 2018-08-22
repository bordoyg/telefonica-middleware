package com.telefonica.portalmiddleware.controller;

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
	
	@Scheduled(initialDelay=5000, fixedDelayString = "${schedule.fixedDelayString}")
	public void renewTokenResponsys(){
		System.out.println("MiddleController renewTokenResponsys");
		try {
			JSONObject jsonObject=tokenResponsysService.service();
			tokenResponsys=jsonObject.getString("authToken");
			endpointResponsys=jsonObject.getString("endPoint");
			
			eventResponsysService.setEndPoint(endpointResponsys);
			eventResponsysService.getHeaders().put("Authorization", tokenResponsys);
			memberResponsysService.setEndPoint(endpointResponsys);
			memberResponsysService.getHeaders().put("Authorization", tokenResponsys);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
