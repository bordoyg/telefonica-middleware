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
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

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
	private Properties portalMiddlewareProperties;
	public Agent_bindingImpl(){
		eventResponsysService = ApplicationContextProvider.getApplicationContext().getBean(EventResponsysService.class);
		memberResponsysService = ApplicationContextProvider.getApplicationContext().getBean(MemberResponsysService.class);
		portalMiddlewareProperties = (Properties)ApplicationContextProvider.getApplicationContext().getBean("propertyConfigurer");
	}
	
    public Message_response_t[] send_message(User_t user, Message_t[] messages) throws RemoteException {
    	Message_response_t[] response=new Message_response_t[1];
    	Message_response_t respItem=new Message_response_t();
    	LOG.debug("Mensaje recibido");
    	Message_t msj=Arrays.asList(messages).iterator().next();
    	
		LOG.debug("message_id: " + msj.getMessage_id());
		LOG.debug("subject: " + msj.getSubject());
		LOG.debug("body: " + msj.getBody());

		try {
			String contactList=portalMiddlewareProperties.getProperty("repsonsys.contact-list");
			
			JSONObject jsonBodyTOA=new JSONObject('{' + msj.getBody() + '}');
			String jsonRequestMemberRS="{'recordData':{'fieldNames':['EMAIL_ADDRESS_'],'records':[['@email@']]},'mergeRule':{'htmlValue':'H','optinValue':'I','textValue':'T','insertOnNoMatch':true,'updateOnMatch':'REPLACE_ALL','matchColumnName1':'EMAIL_ADDRESS_','matchColumnName2':null,'matchOperator':'NONE','optoutValue':'O','rejectRecordIfChannelEmpty':null,'defaultPermissionStatus':'OPTIN'}}";
			jsonRequestMemberRS=jsonRequestMemberRS.replace("@email@", jsonBodyTOA.getString("cemail"));
			memberResponsysService.setUri(memberResponsysService.getUri().replace("@listName@", contactList));
			JSONObject memberResponse=memberResponsysService.service(new JSONObject(jsonRequestMemberRS).toString());
			
			String jsonRequestEventRS="{ 'customEvent': { 'eventNumberDataMapping': null, 'eventDateDataMapping': null, 'eventStringDataMapping': null }, 'recipientData': [ { 'recipient': { 'emailAddress': '@email@', 'listName': { 'folderName': '@folderName@', 'objectName': '@objectName@' }, 'recipientId': null, 'mobileNumber': null, 'emailFormat': 'HTML_FORMAT' }}]}";
			jsonRequestEventRS=jsonRequestEventRS.replace("@email@", jsonBodyTOA.getString("cemail"));
			
			String folder=portalMiddlewareProperties.getProperty("repsonsys.folder");
			jsonRequestEventRS=jsonRequestEventRS.replace("@folderName@", folder);
			jsonRequestEventRS=jsonRequestEventRS.replace("@objectName@", contactList);
			
			eventResponsysService.setUri(eventResponsysService.getUri().replace("@eventName@", msj.getSubject()));
			JSONObject eventResponse=eventResponsysService.service(new JSONObject(jsonRequestEventRS).toString());
			respItem.setStatus("sent");
			LOG.debug("El mensaje " + msj.getMessage_id() + " se envio a Responsys correctamente");
			
		} catch (Throwable e) {
			respItem.setStatus("fault");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); 
			respItem.setData(sStackTrace);
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			respItem.setDescription("Hubo un error: " + timeStamp);
			
			LOG.debug("Hubo un error al intentar enviar el mensaje: " + msj.getMessage_id(), e);
		}

    	respItem.setMessage_id(msj.getMessage_id());
    	
    	
    	response[0]=respItem;
    	
    	return response;
    }

}