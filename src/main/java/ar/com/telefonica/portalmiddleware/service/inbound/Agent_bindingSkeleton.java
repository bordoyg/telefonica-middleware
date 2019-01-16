/**
 * Agent_bindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ar.com.telefonica.portalmiddleware.service.inbound;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import agent.toatech.Agent_port_type;
import agent.toatech.Agent_serviceLocator;
import agent.toatech.Message_response_t;
import agent.toatech.Message_t;
import agent.toatech.User_t;

public class Agent_bindingSkeleton extends agent.toatech.Agent_bindingSkeleton {
	private final Logger LOG = LogManager.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@Override
    public Message_response_t[] send_message(User_t user,Message_t[] messages) throws RemoteException{
		try{
	    	LOG.debug("Mensaje recibido");
	    	Message_t msj=Arrays.asList(messages).iterator().next();
	    	
			LOG.debug("message_id: " + msj.getMessage_id());
			LOG.debug("subject: " + msj.getSubject());
			LOG.debug("body: " + msj.getBody());
	
			ResourceBundle properties = ResourceBundle.getBundle("portal-middleware");
			String surl=properties.getString("service.jump.url");
			LOG.debug("url Jump: " + surl);
			URL url=new URL(surl);
		
			Agent_serviceLocator serviceFactory=new Agent_serviceLocator();
			Agent_port_type service=serviceFactory.getagent_interface(url);
			LOG.debug("Conectando al servicio de JUMP");
			Message_response_t[] response= service.send_message(user, messages);
			LOG.debug("Mensaje enviado a JUMP satisfactoriamente");
			return response;
		}catch(Exception e){
			Message_response_t[] response= new Message_response_t[1];
			Message_response_t respItem=new Message_response_t();
			respItem.setStatus("fault");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); 
			respItem.setData(sStackTrace);
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			respItem.setDescription("Hubo un error: " + timeStamp);
			
			LOG.error("Hubo un error al intentar enviar el mensaje: " + timeStamp, e);
			Message_t msj=Arrays.asList(messages).iterator().next();
			if(msj!=null){
				LOG.error("mensaje id: " +msj.getMessage_id());
				respItem.setMessage_id(msj.getMessage_id());
			}
	    	response[0]=respItem;
	    	return response;
		}
    }
}
