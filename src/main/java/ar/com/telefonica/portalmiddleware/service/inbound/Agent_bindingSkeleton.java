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
		int i=0;
		try{
	    	LOG.debug("INICIO send_message");
	    	
			ResourceBundle properties = ResourceBundle.getBundle("portal-middleware");
			String surl=properties.getString("service.jump.url");
			LOG.debug("url Jump: " + surl);
			URL url=new URL(surl);
			Agent_serviceLocator serviceFactory=new Agent_serviceLocator();
			Agent_port_type service=serviceFactory.getagent_interface(url);
		
			LOG.debug("Conectando al servicio de JUMP");
			//cuando en el body se recibe mas de un mensaje el servicio rompe
			//iterar el array messages[] y hacer una invocacion por cada elemento del array
			Message_response_t[] responses=new Message_response_t[messages.length];
			for(i=0; i<messages.length; i++){
				Message_response_t respItem=new Message_response_t();
				Message_t message=messages[i];
				try{
					Message_t[] messgs=new Message_t[1];
					messgs[0]=message;
					LOG.debug("Enviando mensaje: " + message.getMessage_id());
					Message_response_t[] reps= service.send_message(user, messgs);
					responses[i]=reps[0];
					LOG.debug("Mensaje enviado a JUMP satisfactoriamente");
				}catch(Exception e){
					LOG.error("Hubo un error al intentar enviar el mensaje: " + message.getMessage_id(), e);
					respItem.setStatus("fault");
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					String sStackTrace = sw.toString(); 
					respItem.setData(sStackTrace);
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					respItem.setDescription("Hubo un error: " + timeStamp);
					respItem.setMessage_id(message.getMessage_id());
					responses[i]=respItem;
				}
			}
			LOG.debug("FIN send_message");
			return responses;
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
			
	    	response[i]=respItem;
	    	LOG.debug("FIN send_message");
	    	return response;
		}
    }
}
