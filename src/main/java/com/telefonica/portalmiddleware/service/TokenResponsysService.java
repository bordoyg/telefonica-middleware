package com.telefonica.portalmiddleware.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
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
	
	@Override
	@Value("${tokenResponsysService.endPoint}")
	public void setEndPoint(String endPoint) {
		this.endPoint=endPoint;
		
	}
	@Value("#{${tokenResponsysService.headers}}")
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	@Value("#{${tokenResponsysService.parameters}}")
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
