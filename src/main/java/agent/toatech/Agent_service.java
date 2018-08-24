/**
 * Agent_service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

public interface Agent_service extends javax.xml.rpc.Service {
    public java.lang.String getagent_interfaceAddress();

    public agent.toatech.Agent_port_type getagent_interface() throws javax.xml.rpc.ServiceException;

    public agent.toatech.Agent_port_type getagent_interface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
