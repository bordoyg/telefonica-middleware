package com.telefonica.portalmiddleware.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class MiddleController extends BaseController {
	@Scheduled(initialDelay=5000, fixedDelayString = "${schedule.fixedDelayString}")
	public void renewTokenResponsys(){
		System.out.println("MiddleController renewTokenResponsys");
	}
}
