/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.etb.portalmiddleware.service.inbound;

import org.json.JSONObject;

public class Agent_bindingImpl extends agent.toatech.Agent_bindingImpl{
	@Override
	protected StringBuilder replaceLabelsInBody(JSONObject jsonBodyTOA)throws Exception {
		StringBuilder memberBodyItems=super.replaceLabelsInBody(jsonBodyTOA);
		String result=memberBodyItems.toString().replaceAll("@appt_confirmed@", jsonBodyTOA.getString("appt_confirmed"))
				.replaceAll("@appt_rescheduled@", jsonBodyTOA.getString("appt_rescheduled"))
				.replaceAll("@service_window_start@", jsonBodyTOA.getString("service_window_start"))
				.replaceAll("@service_window_end@", jsonBodyTOA.getString("service_window_end"));
		
		return new StringBuilder(result);
	}
}
