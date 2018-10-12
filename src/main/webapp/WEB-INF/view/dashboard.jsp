
<h1>Portal Middleware - Dashboard v${properties['version']}</h1>
<p><b>Renovaci&oacute;n de token Responsys:</b>  ${properties['schedule.responsysToken.cron']} <b>cron expresion</b></p>
<p><b>Timeout servicios:</b> ${properties['baseService.timeout']} milisegundos</p>
<p><b>Proxy Host:</b> ${properties['baseService.proxyHost']} </p>
<p><b>Proxy Port:</b> ${properties['baseService.proxyPort']}</p>
<p><b>Login Responsys service:</b> ${properties['tokenResponsysService.endPoint']}${properties['tokenResponsysService.uri']} 
<%--<br><b>parameters:</b> ${properties['tokenResponsysService.parameters']} --%></p>
<p><b>Event Responsys service: </b>${properties['eventResponsysService.uri']}</p>
<p><b>Member Responsys service:</b> ${properties['memberResponsysService.uri']}</p>

