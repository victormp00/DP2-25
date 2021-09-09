<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.general-indicators" />
</h2>

<table class="table table-sm">
	<caption>
		<acme:message
			code="administrator.dashboard.form.title.general-indicators" />
	</caption>
	
	
	
	
	
	
	
	
	<!-- principio control check -->
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxFlaggedRatio" /></th>
		<td><acme:print value="${xxxFlaggedRatio}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxratioBudgetZero" /></th>
		<td><acme:print value="${xxxratioBudgetZero}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxaverageGroupByCurrencyBGP" /></th>
		<td><acme:print value="${xxxaverageGroupByCurrency1}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxDeviationGroupByCurrencyBGP" /></th>
		<td><acme:print value="${xxxDeviationGroupByCurrency1}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxaverageGroupByCurrencyEUR" /></th>
		<td><acme:print value="${xxxaverageGroupByCurrency2}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxDeviationGroupByCurrencyEUR" /></th>
		<td><acme:print value="${xxxDeviationGroupByCurrency2}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxaverageGroupByCurrencyUSD" /></th>
		<td><acme:print value="${xxxaverageGroupByCurrency3}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.xxxDeviationGroupByCurrencyUSD" /></th>
		<td><acme:print value="${xxxDeviationGroupByCurrency3}" /></td>
	</tr>
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- final control check  -->
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.publicTasks" /></th>
		<td><acme:print value="${publicTasks}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.privateTasks" /></th>
		<td><acme:print value="${privateTasks}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.ongoingTasks" /></th>
		<td><acme:print value="${ongoingTasks}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.finishedTasks" /></th>
		<td><acme:print value="${finishedTasks}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message 
				code="administrator.dashboard.averageExecTime" /></th>
		<td><acme:print value="${averageExecTime}" />h</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.deviationExecTime" /></th>
		<td><acme:print value="${deviationExecTime}" />h</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.maxExecTime" /></th>
		<td><acme:print value="${maxExecTime}" />h</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.minExecTime" /></th>
		<td><acme:print value="${minExecTime}" />h</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.averageWorkload" /></th>
		<td><acme:print value="${averageWorkload}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.deviationWorkload" /></th>
		<td><acme:print value="${deviationWorkload}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.maxWorkload" /></th>
		<td><acme:print value="${maxWorkload}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.minWorkload" /></th>
		<td><acme:print value="${minWorkload}" /></td>
	</tr>
</table>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.application-statuses" />
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>