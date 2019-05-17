/**
 * Agent_bindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agent.toatech;

public class Agent_bindingSkeleton implements agent.toatech.Agent_port_type, org.apache.axis.wsdl.Skeleton {
    protected static agent.toatech.Agent_port_type impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:toatech:agent", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:toatech:agent", "user_t"), agent.toatech.User_t.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:toatech:agent", "messages"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:toatech:agent", "messages_t"), agent.toatech.Message_t[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("send_message", _params, new javax.xml.namespace.QName("urn:toatech:agent", "message_response"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:toatech:agent", "message_response_t"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:toatech:agent", "send_message"));
        _oper.setSoapAction("agent_service/send_message");
        _myOperationsList.add(_oper);
        if (_myOperations.get("send_message") == null) {
            _myOperations.put("send_message", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("send_message")).add(_oper);
    }

    public Agent_bindingSkeleton() {
    	if(impl==null){
    		impl = new agent.toatech.Agent_bindingImpl();	
    	}
    }

    protected Agent_bindingSkeleton(agent.toatech.Agent_port_type impl) {
    	Agent_bindingSkeleton.impl = impl;
    }
    public agent.toatech.Message_response_t[] send_message(agent.toatech.User_t user, agent.toatech.Message_t[] messages) throws java.rmi.RemoteException
    {
        agent.toatech.Message_response_t[] ret = impl.send_message(user, messages);
        return ret;
    }

}
