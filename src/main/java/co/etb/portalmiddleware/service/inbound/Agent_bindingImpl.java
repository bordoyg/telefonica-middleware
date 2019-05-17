/**
 * Agent_bindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.etb.portalmiddleware.service.inbound;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class Agent_bindingImpl extends agent.toatech.Agent_bindingImpl{
	DateFormat dateFormatIn = new SimpleDateFormat("hh:mm");  
	DateFormat dateFormatOut = new SimpleDateFormat("h:mm a");
	@Override
	protected StringBuilder replaceLabelsInBody(JSONObject jsonBodyTOA)throws Exception {
		StringBuilder memberBodyItems=super.replaceLabelsInBody(jsonBodyTOA);
		
		Date dHourFrom=dateFormatIn.parse(jsonBodyTOA.getString("service_window_start"));
		Date dHourTo=dateFormatIn.parse(jsonBodyTOA.getString("service_window_end"));
		
		String sHourFrom=dateFormatOut.format(dHourFrom);
		String sHourTo=dateFormatOut.format(dHourTo);
		
		String result=memberBodyItems.toString().replaceAll("@appt_confirmed@", jsonBodyTOA.getString("appt_confirmed"))
				.replaceAll("@appt_rescheduled@", jsonBodyTOA.getString("appt_rescheduled"))
				.replaceAll("@service_window_start@", sHourFrom)
				.replaceAll("@service_window_end@", sHourTo);
		
		return new StringBuilder(result);
	}
}
