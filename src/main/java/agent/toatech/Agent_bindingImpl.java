/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

import java.rmi.RemoteException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.telefonica.portalmiddleware.controller.BaseController;
import com.telefonica.portalmiddleware.controller.MiddleController;

public class Agent_bindingImpl implements Agent_port_type{
	private final Logger LOG = LogManager.getLogger(getClass());
    public Message_response_t[] send_message(User_t user, Message_t[] messages) throws RemoteException {
    	Message_response_t[] response=new Message_response_t[1];
    	LOG.debug("Mensaje recibido");
    	for(Message_t msj:messages){
    		LOG.debug("app_port: " + msj.getApp_port());
    		LOG.debug("message_id: " + msj.getMessage_id());
    		LOG.debug("company_id: " + msj.getCompany_id());
    		LOG.debug("address: " + msj.getAddress());
    		LOG.debug("send_to: " + msj.getSend_to());
    		LOG.debug("subject: " + msj.getSubject());
    		LOG.debug("body: " + msj.getBody());
    		
    		
    		//MiddleController middleController=BaseController.getContext().getBean(MiddleController.class);
//    		try {
//				middleController.sendMessage();
//			} catch (Exception e) {
//				LOG.debug("Error al enviar mensaje");
//			}
    		Message_response_t respItem=new Message_response_t();
        	
        	respItem.setData(msj.getBody());
        	respItem.setDescription("Descripcion asd");
        	respItem.setDuration("duration asd");
        	respItem.setExternal_id("extenrnal id asd");
        	respItem.setMessage_id(msj.getMessage_id());
        	respItem.setSent("sent asd");
        	respItem.setStatus("sent");
        	
        	response[0]=respItem;
    	}
    	
    	
    	
    	
    	
    	return response;
    }

}
