package com.telefonica.portalmiddleware.service;

import org.springframework.beans.factory.annotation.Value;

public class MemberResponsysService extends BaseService {
	@Override
	@Value("${memberResponsysService.uri}")
	public void setUri(String uri) {
		this.uri=uri;
		
	}

	@Override
	@Value("${memberResponsysService.method}")
	public void setMethod(String method) {
		this.method=method;
		
	}
}
