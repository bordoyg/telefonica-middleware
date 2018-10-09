/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

import java.rmi.RemoteException;
import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.telefonica.portalmiddleware.service.rest.EventResponsysService;
import com.telefonica.portalmiddleware.service.rest.MemberResponsysService;
import com.telefonica.portalmiddleware.utils.ApplicationContextProvider;

public class Agent_bindingImpl implements Agent_port_type{
	private final Logger LOG = LogManager.getLogger(getClass());
	private EventResponsysService eventResponsysService;
	private MemberResponsysService memberResponsysService;
	public Agent_bindingImpl(){
		eventResponsysService = ApplicationContextProvider.getApplicationContext().getBean(EventResponsysService.class);
		memberResponsysService = ApplicationContextProvider.getApplicationContext().getBean(MemberResponsysService.class);
	}
	
    public Message_response_t[] send_message(User_t user, Message_t[] messages) throws RemoteException {
    	Message_response_t[] response=new Message_response_t[1];
    	Message_response_t respItem=new Message_response_t();
    	LOG.debug("Mensaje recibido");
    	Message_t msj=Arrays.asList(messages).iterator().next();
    	
		LOG.debug("message_id: " + msj.getMessage_id());
		LOG.debug("body: " + msj.getBody());

		try {
			JSONObject jsonBody=new JSONObject('{' + msj.getBody() + '}');
			
			
			//{"recordData":{"fieldNames":["EMAIL_ADDRESS_"],"records":[["sskellor@itba.edu.ar"]]},"mergeRule":{"htmlValue":"H","optinValue":"I","textValue":"T","insertOnNoMatch":true,"updateOnMatch":"REPLACE_ALL","matchColumnName1":"EMAIL_ADDRESS_","matchColumnName2":null,"matchOperator":"NONE","optoutValue":"O","rejectRecordIfChannelEmpty":null,"defaultPermissionStatus":"OPTIN"}}
			memberResponsysService.setRequestEntity(jsonBody.toString());
			JSONObject memberResponse=memberResponsysService.service();
			
			//DiaD1
			//{ "customEvent": { "eventNumberDataMapping": null, "eventDateDataMapping": null, "eventStringDataMapping": null }, "recipientData": [ { "recipient": { "emailAddress": "sskellor@itba.edu.ar", "listName": { "folderName": "!MasterData", "objectName": "CONTACTS_LIST" }, "recipientId": null, "mobileNumber": null, "emailFormat": "HTML_FORMAT" }}]}
			eventResponsysService.setRequestEntity(jsonBody.toString());
			JSONObject eventResponse=eventResponsysService.service();
		} catch (Throwable e) {
			LOG.debug("Hubo un error al intentar enviar el mensaje: " + msj.getMessage_id(), e);
		}

    	respItem.setMessage_id(msj.getMessage_id());
    	respItem.setStatus("sent");
    	
    	response[0]=respItem;
    	
    	return response;
    }

}
