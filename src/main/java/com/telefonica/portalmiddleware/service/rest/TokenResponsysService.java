package com.telefonica.portalmiddleware.service.rest;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix="token-responsys-service")
public class TokenResponsysService extends BaseService {
	@Override
	public void setUri(String uri) {
		this.uri=uri;
		
	}

	@Override
	public void setMethod(String method) {
		this.method=method;
		
	}
	
	@Override
	public void setEndPoint(String endPoint) {
		this.endPoint=endPoint;
		
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
