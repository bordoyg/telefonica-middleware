package com.telefonica.portalmiddleware.service.rest;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.http.HttpHost;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("baseConfigurationService")
public class BaseConfigurationService {
	private @Value("${baseService.timeout}") Integer timeout;
	private @Value("${baseService.proxyPort}") Integer proxyPort;
	private @Value("${baseService.proxyHost}") String proxyHost;
	private RequestConfig requestConfig;
	
	@PostConstruct
	public void init(){
		RequestConfig defaultRequestConfig = RequestConfig.custom()
	            .setCookieSpec(CookieSpecs.DEFAULT)
	            .setExpectContinueEnabled(true)
	            .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
	            .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
	            .build();
		Builder builderConfig=RequestConfig.copy(defaultRequestConfig)
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout);
		
		if(proxyHost!=null && !"".equals(proxyHost.trim())){
			builderConfig= builderConfig.setProxy(new HttpHost(proxyHost, proxyPort));
		}
		requestConfig = builderConfig.build();
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public RequestConfig getRequestConfig() {
		return requestConfig;
	}
	
}
