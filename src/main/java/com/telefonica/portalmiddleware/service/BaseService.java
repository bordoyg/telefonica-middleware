package com.telefonica.portalmiddleware.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class BaseService {
	protected HttpClient httpClient=HttpClients.createDefault();
	
}
