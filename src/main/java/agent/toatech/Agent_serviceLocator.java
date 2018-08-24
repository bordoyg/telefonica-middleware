/**
 * Agent_serviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

public class Agent_serviceLocator extends org.apache.axis.client.Service implements agent.toatech.Agent_service {

    public Agent_serviceLocator() {
    }


    public Agent_serviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Agent_serviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for agent_interface
    private java.lang.String agent_interface_address = "http://localhost:8080";

    public java.lang.String getagent_interfaceAddress() {
        return agent_interface_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String agent_interfaceWSDDServiceName = "agent_interface";

    public java.lang.String getagent_interfaceWSDDServiceName() {
        return agent_interfaceWSDDServiceName;
    }

    public void setagent_interfaceWSDDServiceName(java.lang.String name) {
        agent_interfaceWSDDServiceName = name;
    }

    public agent.toatech.Agent_port_type getagent_interface() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(agent_interface_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getagent_interface(endpoint);
    }

    public agent.toatech.Agent_port_type getagent_interface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            agent.toatech.Agent_bindingStub _stub = new agent.toatech.Agent_bindingStub(portAddress, this);
            _stub.setPortName(getagent_interfaceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setagent_interfaceEndpointAddress(java.lang.String address) {
        agent_interface_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (agent.toatech.Agent_port_type.class.isAssignableFrom(serviceEndpointInterface)) {
                agent.toatech.Agent_bindingStub _stub = new agent.toatech.Agent_bindingStub(new java.net.URL(agent_interface_address), this);
                _stub.setPortName(getagent_interfaceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("agent_interface".equals(inputPortName)) {
            return getagent_interface();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:toatech:agent", "agent_service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:toatech:agent", "agent_interface"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("agent_interface".equals(portName)) {
            setagent_interfaceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
