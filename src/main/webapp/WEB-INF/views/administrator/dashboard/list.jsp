<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="dashboard.publicTasks" path="publicTasks" width="40%"/>
	<acme:list-column code="dashboard.privateTasks" path="privateTasks" width="40%"/>
	<acme:list-column code="dashboard.ongoingTasks" path="ongoingTasks" width="40%"/>
	<acme:list-column code="dashboard.finishedTasks" path="finishedTasks" width="40%"/>
	<acme:list-column code="dashboard.averageExecTime" path="averageExecTime" width="40%"/>
	<acme:list-column code="dashboard.deviationExecTime" path="deviationExecTime" width="40%"/>
	<acme:list-column code="dashboard.maxExecTime" path="maxExecTime" width="40%"/>
	<acme:list-column code="dashboard.minExecTime" path="minExecTime" width="40%"/>
	<acme:list-column code="dashboard.averageWorkload" path="averageWorkload" width="40%"/>
	<acme:list-column code="dashboard.deviationWorkload" path="deviationWorkload" width="40%"/>
	<acme:list-column code="dashboard.maxWorkload" path="maxWorkload" width="40%"/>
	<acme:list-column code="dashboard.minWorkload" path="minWorkload" width="40%"/>
</acme:list>
