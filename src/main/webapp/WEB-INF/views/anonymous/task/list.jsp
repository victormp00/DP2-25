<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="task.title" path="title" width="20%"/>
	<acme:list-column code="task.creation" path="creation" width="40%"/>
	<acme:list-column code="task.finish" path="finish" width="40%"/>
	<acme:list-column code="task.workload" path="workload" width="40%"/>
	<acme:list-column code="task.description" path="description" width="40%"/>
	<acme:list-column code="task.link" path="link" width="40%"/>		
</acme:list>
