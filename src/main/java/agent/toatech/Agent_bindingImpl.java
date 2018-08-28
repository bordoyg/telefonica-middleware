/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

import java.rmi.RemoteException;

public class Agent_bindingImpl implements Agent_port_type{
    public Message_response_t[] send_message(User_t user, Message_t[] messages) throws RemoteException {
    	Message_response_t[] response=new Message_response_t[1];
    	Message_response_t respItem=new Message_response_t();
    	
    	respItem.setData("data asd");
    	respItem.setDescription("Descripcion asd");
    	respItem.setDuration("duration asd");
    	respItem.setExternal_id("extenrnal id asd");
    	respItem.setMessage_id(123L);
    	respItem.setSent("sent asd");
    	respItem.setStatus("status asd");
    	
    	response[0]=respItem;
    	
    	return response;
    }

}
