<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<h1>Portal Middleware - Dashboard </h1>
<p>Renovaci&oacute;n de token Responsys:<spring:eval expression="@propertyConfigurer.getProperty('schedule.fixedDelayString')" /> milisegundos</p>
