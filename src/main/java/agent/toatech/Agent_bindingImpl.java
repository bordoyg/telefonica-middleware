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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.telefonica.portalmiddleware.controller.BaseController;
import com.telefonica.portalmiddleware.controller.MiddleController;
import com.telefonica.portalmiddleware.utils.ApplicationContextProvider;

public class Agent_bindingImpl implements Agent_port_type{
	private final Logger LOG = LogManager.getLogger(getClass());
	private MiddleController middlewareController;
	public Agent_bindingImpl(){
		middlewareController = ApplicationContextProvider.getApplicationContext().getBean(MiddleController.class);
	}
	
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

    		try {
				middlewareController.sendMessage();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Message_response_t respItem=new Message_response_t();

        	respItem.setMessage_id(msj.getMessage_id());
        	respItem.setStatus("sent");
        	
        	response[0]=respItem;
    	}
    	
    	
    	
    	
    	
    	return response;
    }

}
