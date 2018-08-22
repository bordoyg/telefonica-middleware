package com.telefonica.portalmiddleware.service;

import org.springframework.beans.factory.annotation.Value;

public class TokenResponsysService extends BaseService {
	@Override
	@Value("${tokenResponsysService.uri}")
	public void setUri(String uri) {
		this.uri=uri;
		
	}

	@Override
	@Value("${tokenResponsysService.method}")
	public void setMethod(String method) {
		this.method=method;
		
	}
}
