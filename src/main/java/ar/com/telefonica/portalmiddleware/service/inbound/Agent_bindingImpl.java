/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ar.com.telefonica.portalmiddleware.service.inbound;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.telefonica.portalmiddleware.service.rest.BaseService;
import com.telefonica.portalmiddleware.utils.Utils;

import agent.toatech.Agent_port_type;
import agent.toatech.Agent_serviceLocator;
import agent.toatech.Message_response_t;
import agent.toatech.Message_t;
import agent.toatech.User_t;

public class Agent_bindingImpl extends agent.toatech.Agent_bindingImpl{
	private final Logger LOG = LogManager.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	private DocumentBuilder builder;  
	private Agent_port_type service=null;
	private String portalUrl=null;
	private static final String FAKE_FAILED_STATUS="fault";
	
    public Agent_bindingImpl() {
        super();
        init();
    }
    private void init(){
        try {
			builder = factory.newDocumentBuilder();
			ResourceBundle properties = ResourceBundle.getBundle("portal-middleware");
			String jumpUrl=properties.getString("service.jump.url");
			portalUrl=properties.getString("portal.url");
			LOG.debug("url Jump: " + jumpUrl);
			LOG.debug("url portal: " + portalUrl);
			URL url=new URL(jumpUrl);
			Agent_serviceLocator serviceFactory=new Agent_serviceLocator();
			service=serviceFactory.getagent_interface(url);
		} catch (Exception e) {
			LOG.error(e);
		}

    }
    @Override
	public Message_response_t[] send_message(User_t user, Message_t[] messages) throws RemoteException {
		int i=0;

    	LOG.debug("INICIO send_message");
		//cuando en el body se recibe mas de un mensaje el servicio rompe
		//iterar el array messages[] y hacer una invocacion por cada elemento del array
		Message_response_t[] responses=new Message_response_t[messages.length];
		for(i=0; i<messages.length; i++){
			Message_response_t respItem=new Message_response_t();
			Message_t message=messages[i];
			try{
				LOG.debug("Modificando mensaje: " + message.getMessage_id());
				addDataToMessage(message, portalUrl);
				Message_t[] messgs=new Message_t[1];
				messgs[0]=message;
				LOG.debug("Enviando mensaje: " + message.getMessage_id());
				LOG.debug("Conectando al servicio de JUMP");
				Message_response_t[] reps= service.send_message(user, messgs);
				LOG.debug("Mensaje enviado a JUMP, analizando la respuesta");
				if(FAKE_FAILED_STATUS.equals(reps[0].getStatus())){
					reps[0].setStatus(BaseService.STATUS_FAILED);
					LOG.debug("Mensaje no pudo ser enviado por JUMP: " + reps[0]);
				}
				if(BaseService.STATUS_SENT.equals(reps[0].getStatus()) 
						|| BaseService.STATUS_DELIVERED.equals(reps[0].getStatus())){
					LOG.debug("Mensaje fue procesado por JUMP satisfactoriamente");
				}
				responses[i]=reps[0];
				
			}catch(Exception e){
				LOG.error("Hubo un error al intentar enviar el mensaje: " + message.getMessage_id(), e);
				respItem.setStatus(BaseService.STATUS_FAILED);
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				respItem.setData(e.getMessage());
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				respItem.setDescription("Hubo un error: " + timeStamp);
				respItem.setMessage_id(message.getMessage_id());
				responses[i]=respItem;
			}
		}
		LOG.debug("FIN send_message");

		return responses; 
	}
	private void addDataToMessage(Message_t message, String portalUrl) throws Exception {
	    Document document = builder.parse(new InputSource(new StringReader(message.getBody())));  
	    String aid=document.getElementsByTagName("aid").item(0).getTextContent();
	    String encriptedAid=Utils.encrypt(aid);
	    String activityUrl=portalUrl + "?" + encriptedAid.replaceAll("=", "");
	    LOG.debug("ID de la activodad= " + aid);
	    LOG.debug("URL Portal= " + activityUrl);
		StringBuilder sb=new StringBuilder();
		sb.append(message.getBody());
		sb=sb.replace(sb.indexOf("<aid>"), sb.indexOf("<aid>") + "<aid>".length(), "<property><label>activityurl</label><value>" + activityUrl + "</value></property><aid>");
		message.setBody(sb.toString());
		
	}
}
