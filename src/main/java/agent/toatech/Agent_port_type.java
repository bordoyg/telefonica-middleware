/**
 * Agent_port_type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

public interface Agent_port_type extends java.rmi.Remote {
    public agent.toatech.Message_response_t[] send_message(agent.toatech.User_t user, agent.toatech.Message_t[] messages) throws java.rmi.RemoteException;
}
