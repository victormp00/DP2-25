<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="taskId"/>
	
	<acme:form-textbox code="task.title" path="title" />
	<acme:form-textbox code="task.executionPeriod" path="executionPeriod"/>
	<acme:form-textbox code="task.workload" path="workload" />
	<acme:form-textbox code="task.description" path="description"/>
	<!-- deje este asi para probar -->
	<acme:form-textbox code="task.link" path="link" readonly="true"/>
		
  	<acme:form-submit code="task.button.create" action="task/create"/>
</acme:form>
