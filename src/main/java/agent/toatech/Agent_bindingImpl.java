/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

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
	public Agent_bindingImpl(){
		eventResponsysService = ApplicationContextProvider.getApplicationContext().getBean(EventResponsysService.class);
		memberResponsysService = ApplicationContextProvider.getApplicationContext().getBean(MemberResponsysService.class);
		portalMiddlewareProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("propertyConfigurer");
	}
	
    public Message_response_t[] send_message(User_t user, Message_t[] messages) throws RemoteException {
    	int i=0;
    	LOG.debug("INICIO send_message");
    	Message_response_t[] responses=new Message_response_t[messages.length];
		for(i=0; i<messages.length; i++){
			Message_response_t respItem=new Message_response_t();
			Message_t message=messages[i];
			
			LOG.debug("message_id: " + message.getMessage_id());
			LOG.debug("subject: " + message.getSubject());
			LOG.debug("body: " + message.getBody());

			try {
				String contactList=portalMiddlewareProperties.getProperty("repsonsys.contact-list");
				JSONObject jsonBodyTOA=new JSONObject('{' + message.getBody() + '}');
				String jsonRequestMemberRS="{'recordData':{'fieldNames':['EMAIL_ADDRESS_','URL_CONFIRMAR','URL_CANCELAR','URL_MODIFICAR','URL_UBIC_TECNICO', 'MOBILE_NUMBER_', 'MOBILE_COUNTRY_', 'FECHA_CITA', 'FRANJA_HORARIA', 'NOMBRE','NOMBRE_TECNICO'],'records':[['@email@', '@urlPortal@', '@urlPortal@', '@urlPortal@', '@urlPortal@', '@mobileNumber@', '@countryCode@', '@fechaCita@', '@franjaHoraria@', '@nombre@', '@nombreTecnico@']]},'mergeRule':{'htmlValue':'H','optinValue':'I','textValue':'T','insertOnNoMatch':true,'updateOnMatch':'REPLACE_ALL','matchColumnName1':'EMAIL_ADDRESS_','matchColumnName2':null,'matchOperator':'NONE','optoutValue':'O','rejectRecordIfChannelEmpty':null,'defaultPermissionStatus':'OPTIN'}}";
				
				jsonRequestMemberRS=jsonRequestMemberRS.replaceAll("@email@", jsonBodyTOA.getString("cemail"))
						.replaceAll("@urlPortal@", createURLPortal(jsonBodyTOA.getInt("aid")))
						.replaceAll("@mobileNumber@", jsonBodyTOA.getString("ccell"))
						.replaceAll("@countryCode@", jsonBodyTOA.getString("country_code"))
						.replaceAll("@fechaCita@", jsonBodyTOA.getString("route_date"))
						.replaceAll("@franjaHoraria@", jsonBodyTOA.getString("activity_time_slot"))
						.replaceAll("@nombre@", jsonBodyTOA.getString("activity_customer_name"))
						.replaceAll("@nombreTecnico@", jsonBodyTOA.getString("resource_name"));
				
				String urlMember=portalMiddlewareProperties.getProperty("memberResponsysService.uri");
				memberResponsysService.setUri(urlMember.replace("@listName@", contactList));
				JSONObject memberResponse=memberResponsysService.service(new JSONObject(jsonRequestMemberRS).toString());
				if(memberResponse!=null && memberResponse.has("errorCode")){
					throw new Exception("No pudo crearse el contacto en responsys: " + memberResponse.toString());
				}
				
				String jsonRequestEventRS="{ 'customEvent': { 'eventNumberDataMapping': null, 'eventDateDataMapping': null, 'eventStringDataMapping': null }, 'recipientData': [ { 'recipient': { 'emailAddress': '@email@', 'listName': { 'folderName': '@folderName@', 'objectName': '@objectName@' }, 'recipientId': null, 'mobileNumber': null, 'emailFormat': 'HTML_FORMAT' }}]}";
				jsonRequestEventRS=jsonRequestEventRS.replace("@email@", jsonBodyTOA.getString("cemail"));
				
				String folder=portalMiddlewareProperties.getProperty("repsonsys.folder");
				jsonRequestEventRS=jsonRequestEventRS.replace("@folderName@", folder);
				jsonRequestEventRS=jsonRequestEventRS.replace("@objectName@", contactList);
				
				String urlEvent=portalMiddlewareProperties.getProperty("eventResponsysService.uri");
				eventResponsysService.setUri(urlEvent.replace("@eventName@", message.getSubject()));
				JSONObject eventResponse=eventResponsysService.service(new JSONObject(jsonRequestEventRS).toString());
				if(eventResponse!=null && !eventResponse.has("success") || !eventResponse.getBoolean("success")){
					throw new Exception("El mensaje no pudo ser enviado: " + eventResponse.toString());
				}
				respItem.setStatus(BaseService.STATUS_SENT);
				LOG.debug("El mensaje " + message.getMessage_id() + " se envio a Responsys correctamente");
			} catch (Throwable e) {
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				
				respItem.setDescription("Hubo un error: " + timeStamp);
				respItem.setStatus(BaseService.STATUS_FAILED);
				respItem.setData(e.getMessage());
				
				LOG.debug("Hubo un error al intentar enviar el mensaje: " + message.getMessage_id(), e);
			}

	    	respItem.setMessage_id(message.getMessage_id());
	    	
	    	
	    	responses[i]=respItem;
    	}
    	
    	return responses;
    }

	private String createURLPortal(int aid) throws Exception {
		String url=portalMiddlewareProperties.getProperty("portal.url");
		String encriptedAid=Utils.encrypt(String.valueOf(aid));
		return url + "?" + encriptedAid.replaceAll("=", "");
	}
}
