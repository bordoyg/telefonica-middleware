package com.telefonica.portalmiddleware.service.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EventResponsysService extends BaseService{

	@Override
	@Value("${eventResponsysService.uri}")
	public void setUri(String uri) {
		this.uri=uri;
	}

	@Override
	@Value("${eventResponsysService.method}")
	public void setMethod(String method) {
		this.method=method;	
	}
}
