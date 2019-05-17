/**
 * Agent_bindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.etb.portalmiddleware.service.inbound;

public class Agent_bindingSkeleton extends agent.toatech.Agent_bindingSkeleton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void initImpl() {
		if(impl==null){
			impl = new Agent_bindingImpl();
		}
    }
}
