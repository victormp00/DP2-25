
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
 <acme:form-textbox code="anonymous.task.form.title" path="title"/>
    <acme:form-textarea code="anonymous.task.form.description" path="description"/>
    <acme:form-moment code="anonymous.task.form.creation" path="creation"/>
    <acme:form-moment code="anonymous.task.form.finish" path="finish"/>
    <acme:form-textbox code="anonymous.task.form.workload" path="workload"/>
    <acme:form-textbox code="anonymous.task.form.link" path="link"/>

	<acme:form-submit test="${command == 'create'}" code="authenticated.manager.manager.task.form.button.create" action="/manager/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.manager.manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'delete'}" code="authenticated.manager.manager.task.form.button.delete" action="/manager/task/delete"/>		
	<acme:form-return code="anonymous.task.form.button.return"/>

</acme:form>