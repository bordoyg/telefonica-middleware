package com.telefonica.portalmiddleware.service.rest;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.Charsets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

public abstract class BaseService {
	protected String endPoint;
	protected String uri;
	protected String method;
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	@Qualifier("propertyConfigurer")
	private Properties portalMiddlewareProperties;
	@Autowired
	@Qualifier("baseConfigurationService")
	protected BaseConfigurationService baseConfiguration;
	private String jsonEntity;
	
	protected Map<String, String> headers=new HashMap<String, String>();
	protected Map<String, String> parameters=new HashMap<String, String>();
	
    private final Log LOG = LogFactory.getLog(getClass());

	@PostConstruct
	public void init(){ 
		
		String[] beanNames=appContext.getBeanNamesForType(this.getClass());
		for(String beanName:beanNames){
			try{
				String endPoint=portalMiddlewareProperties.getProperty(beanName + ".endPoint");
				String uri=portalMiddlewareProperties.getProperty(beanName + ".uri");
				String method=portalMiddlewareProperties.getProperty(beanName + ".method");
				String parameters=portalMiddlewareProperties.getProperty(beanName + ".parameters");
				String headers=portalMiddlewareProperties.getProperty(beanName + ".headers");
	
				try{
					JSONObject jsonParameters=new JSONObject(parameters);
					Iterator<String> nameItr = jsonParameters.keys();
					while(nameItr.hasNext()) {
					    String name = nameItr.next();
					    this.getParameters().put(name, jsonParameters.getString(name));
					}
				}catch(Exception je){}
				
				try{
					JSONObject jsonHeaders=new JSONObject(headers);
					Iterator<String> nameItr = jsonHeaders.keys();
					while(nameItr.hasNext()) {
					    String name = nameItr.next();
					    this.getHeaders().put(name, jsonHeaders.getString(name));
					}
				}catch(Exception je){}
				
				this.setEndPoint(endPoint);
				this.setUri(uri);
				this.setMethod(method);
			}catch(Exception e){
				LOG.error("Hubo un error al levantar la configuracion", e);
			}
		}
	}
	public JSONObject service()throws Throwable{
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
		try{
			HttpRequestBase httpRequest=null;
			URIBuilder builder = new URIBuilder(endPoint + uri);
			if(parameters!=null){
				for(Entry<String, String> entity: parameters.entrySet()){
					builder.setParameter(entity.getKey(), entity.getValue());
				}	
			}
			if(HttpGet.METHOD_NAME.compareTo(method)==0){
				httpRequest=new HttpGet(builder.build());
			}
			if(HttpPost.METHOD_NAME.compareTo(method)==0){
			    httpRequest=new HttpPost(builder.build());
			    if(jsonEntity!=null){
			    	StringEntity entity = new StringEntity(jsonEntity);
				    ((HttpPost)httpRequest).setEntity(entity);	
			    }
			}
			if(headers!=null){
				for(Entry<String, String> entity: headers.entrySet()){
					httpRequest.setHeader(entity.getKey(), entity.getValue());
				}	
			}
			if(httpRequest!=null){
				httpRequest.setConfig(baseConfiguration.getRequestConfig());
			}
			CloseableHttpResponse response=httpClient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			Header encodingHeader = entity.getContentEncoding();
			
			// you need to know the encoding to parse correctly
			Charset encoding = encodingHeader == null ? StandardCharsets.UTF_8 : Charsets.toCharset(encodingHeader.getValue());
			
			// use org.apache.http.util.EntityUtils to read json as string
			String json = EntityUtils.toString(entity, encoding);
			
			LOG.debug("Raw entity response: " + json);
			if(response.getStatusLine().getStatusCode() < 200 
					|| response.getStatusLine().getStatusCode() >= 400){
				response.close();
				throw new Exception("hubo un error devuelto por el servicio: " + json );
			}
			
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.has("errorCode")){
				response.close();
				throw new Exception("hubo un error devuelto por el servicio: " + json );
			}
			return jsonObject;
		}catch(Throwable e){
			throw e;
		}finally{
			httpClient.close();
		}
	}

	public String getUri(){ 
		return uri;
	}
	
	public void setUri(String uri){
		this.uri=uri;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method){
		this.method=method;
	}
	
	public String getJsonEntity() {
		return jsonEntity;
	}
	public void setJsonEntity(String jsonEntity) {
		this.jsonEntity = jsonEntity;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	
}
