package com.telefonica.portalmiddleware.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController extends BaseController{
	@RequestMapping("/dashboard")
	public ModelAndView helloWorld() {
		System.out.println("DashboardController telefonica");
		String message = "ya no es lo que era, ya no es lo que era...";
		return new ModelAndView("dashboard", "message", message);
	}

}
