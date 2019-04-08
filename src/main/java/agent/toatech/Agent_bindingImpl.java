/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.telefonica.portalmiddleware.service.rest.BaseService;
import com.telefonica.portalmiddleware.service.rest.EventResponsysService;
import com.telefonica.portalmiddleware.service.rest.MemberResponsysService;
import com.telefonica.portalmiddleware.utils.ApplicationContextProvider;
import com.telefonica.portalmiddleware.utils.Utils;

public class Agent_bindingImpl implements Agent_port_type{
	private final Logger LOG = LogManager.getLogger(getClass());
	private EventResponsysService eventResponsysService;
	private MemberResponsysService memberResponsysService;
	private Properties portalMiddlewareProperties;
	private String contactList;
	
	public Agent_bindingImpl(){
		eventResponsysService = ApplicationContextProvider.getApplicationContext().getBean(EventResponsysService.class);
		memberResponsysService = ApplicationContextProvider.getApplicationContext().getBean(MemberResponsysService.class);
		portalMiddlewareProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("propertyConfigurer");
		contactList=portalMiddlewareProperties.getProperty("repsonsys.contact-list");
	}
	public Message_response_t[] send_message(User_t user, Message_t[] messages) throws RemoteException {
    	
    	LOG.debug("BEGIN send_message");
    	Message_response_t[] responses=new Message_response_t[messages.length];
    	
    	String eventName=messages[0].getSubject();
    	String memberBodyItems=buildBodyMemberRequest(messages, responses);
    	String eventBodyItems=buildBodyEventRequest(messages, responses);
    	
    	JSONObject memberResponse=null;
    	try{
        	String urlMember=portalMiddlewareProperties.getProperty("memberResponsysService.uri");
    		memberResponsysService.setUri(urlMember.replace("@listName@", contactList));
    		memberResponse=(JSONObject)memberResponsysService.service(new JSONObject(memberBodyItems).toString());
    		if(memberResponse!=null && memberResponse.has("errorCode")){
    			throw new Exception("Error on member service: " + memberResponse.toString());
    		}
    	}catch(Throwable t){
    		for(int i=0; i<messages.length; i++){
    			if(responses[i]==null){
    				Message_response_t respItem=new Message_response_t();
        			Message_t message=messages[i];
    				respItem.setStatus(BaseService.STATUS_FAILED);
    				respItem.setData(t.getMessage());
    				respItem.setMessage_id(message.getMessage_id());
    				responses[i]=respItem;
    			}
    		}
    		return responses;
    	}
    	
    	JSONArray eventResponse=null;
    	try{
    		String urlEvent=portalMiddlewareProperties.getProperty("eventResponsysService.uri");
    		eventResponsysService.setUri(urlEvent.replace("@eventName@", eventName));
    		eventResponse=(JSONArray)eventResponsysService.service(new JSONObject(eventBodyItems).toString());
    	}catch(Throwable t){
    		for(int i=0; i<messages.length; i++){
    			if(responses[i]==null){
    				Message_response_t respItem=new Message_response_t();
        			Message_t message=messages[i];
    				respItem.setStatus(BaseService.STATUS_FAILED);
    				respItem.setData(t.getMessage());
    				respItem.setMessage_id(message.getMessage_id());
    				responses[i]=respItem;
    			}
    		}
    		return responses;
    	}

		responses = processResponse(messages, memberResponse, eventResponse);
		LOG.debug("END send_message");
    	return responses;
    }
	
	
    private Message_response_t[] processResponse(Message_t[] messages, JSONObject memberResponse, JSONArray eventResponse) {
    	
    	JSONArray records=memberResponse.getJSONObject("recordData").getJSONArray("records");
    	Message_response_t[] responses=new Message_response_t[records.length()];
    	
    	for(int i=0; i<records.length(); i++){
    		String record=records.getJSONArray(i).getString(0);
    		
    		LOG.debug("Response member service: " + record);
    	}
    	for(int i=0; i<eventResponse.length(); i++){
    		Message_response_t respItem=new Message_response_t();
    		JSONObject eventItem=(JSONObject)eventResponse.get(i);
    		
    		if(eventItem!=null && !eventItem.has("success") || !eventItem.getBoolean("success")){
    			respItem.setStatus(BaseService.STATUS_FAILED);
    			respItem.setDescription(eventItem.getString("errorMessage"));
    		}else{
    			respItem.setStatus(BaseService.STATUS_SENT);
    		}
			respItem.setMessage_id(messages[i].getMessage_id());
			responses[i]=respItem;
    	}
    	
		return responses;
	}

	private String createURLPortal(int aid) throws Exception {
		String url=portalMiddlewareProperties.getProperty("portal.url");
		String encriptedAid=Utils.encrypt(String.valueOf(aid));
		return url + "?" + encriptedAid.replaceAll("=", "");
	}
	private String buildBodyMemberRequest(Message_t[] messages, Message_response_t[] responses){
		String jsonRequestMemberRS=portalMiddlewareProperties.getProperty("memberResponsysService.body");
		StringBuilder memberBodyItems=new StringBuilder();
		for(int i=0; i<messages.length; i++){
			Message_t message=messages[i];
			
			LOG.debug("message_id: " + message.getMessage_id());
			LOG.debug("subject: " + message.getSubject());
			LOG.debug("body: " + message.getBody());

			try {
				JSONObject jsonBodyTOA=new JSONObject('{' + message.getBody() + '}');
				memberBodyItems.append(replaceLabelsInBody(jsonBodyTOA));
			} catch (Throwable e) {
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				
				LOG.debug("Error on parse request: " + timeStamp + ", id message: " + message.getMessage_id() , e);
				Message_response_t respItem=new Message_response_t();
				respItem.setDescription("Error on parse request: " + timeStamp + ", id message: " + message.getMessage_id() + " cause: "  + e.getMessage());
				respItem.setStatus(BaseService.STATUS_FAILED);
				respItem.setData(e.getMessage());
				respItem.setMessage_id(message.getMessage_id());
				responses[i]=respItem;
			}
    	}
		jsonRequestMemberRS=jsonRequestMemberRS.replace("@bodyItems@", memberBodyItems.replace(memberBodyItems.lastIndexOf(","), memberBodyItems.length(), ""));
		return jsonRequestMemberRS.toString();
	}
	protected StringBuilder replaceLabelsInBody(JSONObject jsonBodyTOA) throws Exception {
		String bodyItems=portalMiddlewareProperties.getProperty("memberResponsysService.body.items");
		StringBuilder memberBodyItems=new StringBuilder();
		memberBodyItems.append(new String(bodyItems).replaceAll("@email@", jsonBodyTOA.getString("cemail"))
				.replaceAll("@urlPortal@", createURLPortal(jsonBodyTOA.getInt("aid")))
				.replaceAll("@mobileNumber@", jsonBodyTOA.getString("ccell"))
				.replaceAll("@countryCode@", jsonBodyTOA.getString("country_code"))
				.replaceAll("@fechaCita@", jsonBodyTOA.getString("route_date"))
				.replaceAll("@franjaHoraria@", jsonBodyTOA.getString("activity_time_slot"))
				.replaceAll("@nombre@", jsonBodyTOA.getString("activity_customer_name"))
				.replaceAll("@nombreTecnico@", jsonBodyTOA.getString("resource_name")));
		memberBodyItems.append(", ");
		return memberBodyItems;
	}
	private String buildBodyEventRequest(Message_t[] messages, Message_response_t[] responses){
		String jsonRequestEventRS=portalMiddlewareProperties.getProperty("eventResponsysService.body");
		String bodyItems=portalMiddlewareProperties.getProperty("eventResponsysService.body.items");
		String folder=portalMiddlewareProperties.getProperty("repsonsys.folder");
		
		StringBuilder eventBodyItems=new StringBuilder();
		for(int i=0; i<messages.length; i++){
			Message_t message=messages[i];
			
			LOG.debug("message_id: " + message.getMessage_id());
			LOG.debug("subject: " + message.getSubject());
			LOG.debug("body: " + message.getBody());

			try {
				JSONObject jsonBodyTOA=new JSONObject('{' + message.getBody() + '}');
				eventBodyItems.append(new String(bodyItems).replaceAll("@email@", jsonBodyTOA.getString("cemail"))
						.replaceAll("@folderName@", folder)
						.replaceAll("@objectName@", contactList));
				eventBodyItems.append(", ");
			} catch (Throwable e) {
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				
				LOG.debug("Error on parse request: " + timeStamp + ", id message: " + message.getMessage_id() , e);
				if(responses[i]!=null){
					Message_response_t respItem=new Message_response_t();
					respItem.setDescription("Error on parse request: " + timeStamp + ", id message: " + message.getMessage_id() + " cause: "  + e.getMessage());
					respItem.setStatus(BaseService.STATUS_FAILED);
					respItem.setData(e.getMessage());
					respItem.setMessage_id(message.getMessage_id());
					responses[i]=respItem;
				}
			}
    	}
		
    	jsonRequestEventRS=jsonRequestEventRS.replace("@bodyItems@", eventBodyItems.replace(eventBodyItems.lastIndexOf(","), eventBodyItems.length(), ""));
		return jsonRequestEventRS;
	}
}
