import java.net.URL;
import java.util.Date;

import agent.toatech.Agent_port_type;
import agent.toatech.Agent_serviceLocator;
import agent.toatech.Message_response_t;
import agent.toatech.Message_t;
import agent.toatech.User_t;

public class SendMessageJumpTest {

	public static void main(String[] args) {
		try{
			URL url=new URL("http://10.167.52.70:8003/toaOutboundService-Adapter/EXP/toaOutboundService-AdapterPipeline");
			Agent_serviceLocator serviceFactory=new Agent_serviceLocator();
			Agent_port_type service=serviceFactory.getagent_interface(url);
			User_t user=new User_t();
			Message_t[] messages=new Message_t[1];
			Message_t message =new Message_t();
			messages[0]=message;
			
			user.setAuth_string("844f1532ea625c825eb273c26d053c08");
			user.setLogin("app_user");
			user.setCompany("telefonica-ar1.test");
			user.setNow(new Date().toString());
			String body="<![CDATA[<SMART_COMUNICATION_DAY_BEFORE><time_creation>2018-11-23 13:45</time_creation><cname>ordenprovisionuno smartwebprovisioindividuo</cname>			<customer_number>326626566</customer_number><cell>1149288947</cell>			<phone></phone><ACTIVITY_GROUP>Provision.DayBefore</ACTIVITY_GROUP>			<ID_TICKET>268486296</ID_TICKET>			<ID_CASE></ID_CASE>			<cancelReason>NA</cancelReason>			<appt_number>OMS3423265</appt_number>			<address>CLLE: AV CORDOBA NRO: 01401 ENTR: PARAGUAY ENTR: PARANA ENTR: URUGUAY CORX: -34.599340 CORY: -58.386798 CP:(1055) LOC: CAPITAL FEDERAL - PROV: CAPITAL FEDERAL</address>			<city>CAPITAL FEDERAL</city>			<state>C</state>			<activity_worktype>Instalar</activity_worktype>			<activity_worktype_id>60</activity_worktype_id>			<activity_worktype_label>PRO_INSTALL</activity_worktype_label>			<date>2018-11-24</date>			<time_slot>AM</time_slot>			<aid>5013082</aid>			<astatus>pending</astatus>			<pname>JUNCAL</pname>			<external_id>BK_JUNCAL</external_id>			<property>			<label>XA_RED_ZONE</label>			<value></value>			</property>			<property>			<label>XA_CUSTOMER_TYPE</label>			<value>INDIVIDUO</value>			</property>			<property>			<label>XA_CONTACT_NAME</label>			<value>ordenprovisionuno smartwebprovisioindividuo</value>			</property>			<property>			<label>XA_ORDER_CREATION_DATE</label>			<value>2018-11-23 03:15</value>			</property>			<property>			<label>XA_PRIORITY</label>			<value>9</value>			</property>			<property>			<label>XA_APPOINTMENT_SCHEDULER</label>			<value>Cliente</value>			</property>			<property>			<label>XA_INITIAL_DATE</label>			<value>2018-11-24</value>			</property>			<property>			<label>XA_PRODUCTS_SERVICES</label>			<value>&lt;XA_PRODUCTS_SERVICES&gt;&lt;productservice&gt;&lt;codigo&gt;Línea Fija&lt;/codigo&gt;&lt;descripcion&gt;Phone&lt;/descripcion&gt;&lt;/productservice&gt;&lt;productservice&gt;&lt;codigo&gt;Banda Ancha&lt;/codigo&gt;&lt;descripcion&gt;Broadband&lt;/descripcion&gt;&lt;/productservice&gt;&lt;productservice&gt;&lt;codigo&gt;Aplicaciones Digitales&lt;/codigo&gt;&lt;descripcion&gt;DigitalApplication&lt;/descripcion&gt;&lt;/productservice&gt;&lt;/XA_PRODUCTS_SERVICES&gt;</value>			</property>			<property>			<label>XA_MULTIPRODUCTO</label>			<value>Broadband_Main+VOIP</value>			</property>			<property>			<label>XA_SYMPTOM</label>			<value></value>			</property>			<property>			<label>XA_CRONICO</label>			<value></value>			</property>			<property>			<label>XA_SOURCE_SYSTEM</label>			<value>NOTIFICASMS</value>			</property>			<property>			<label>XR_COMPANY_NAME</label>			<value></value>			</property>			<property>			<label>XR_REAL_COMPANY</label>			<value></value>			</property>			<property>			<label>XA_HISTORY_REPLY</label>			<value></value>			</property>			<property>			<label>XA_REMINDER_REPLY</label>			<value></value>			</property>			<property>			<label>XA_REMINDER_DONE</label>			<property>			<value></value>			</property><label>XA_DATETIME_REPLY</label><value></value></property><property><label>XA_NUMBER_SERVICE_ORDER</label><value>268486296</value></property></SMART_COMUNICATION_DAY_BEFORE>]]>";
			message.setApp_host("test-etabeapp-3.toa.usdc2.oraclecloud.com");
			message.setApp_port(17401);
			message.setApp_url("/outbound");
			message.setMessage_id(1706276415);
			message.setAddress("mmm");
			message.setSend_to(new Date().toString());
			message.setSubject("SMART_COMUNICATION_DAY_BEFORE");
			message.setBody(body);
			
			Message_response_t[] response= service.send_message(user, messages);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
