
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
 <acme:form-textbox code="authenticated.manager.task.form.title" path="title"/>
    <acme:form-textarea code="authenticated.manager.task.form.description" path="description"/>
    <jstl:if test="${command == 'show'}">
    <acme:form-moment code="authenticated.manager.form.creation" path="creation"/> 
    <acme:form-moment code="authenticated.manager.form.finish" path="finish"/>
    </jstl:if>
    <jstl:if test="${command == 'update'}">
    <acme:form-moment code="authenticated.manager.form.creation" path="creation"/> 
    <acme:form-moment code="authenticated.manager.form.finish" path="finish"/>
    <acme:form-checkbox code="authenticated.manager.task.form.finished" path="finished"/>
    </jstl:if>
    <jstl:if test="${command == 'create'}">
    <acme:form-moment code="authenticated.manager.form.creation" path="creation"/> 
    <acme:form-moment code="authenticated.manager.form.finish" path="finish"/>
    </jstl:if>
   	<acme:form-textbox code="authenticated.manager.form.workload" path="workload"/>
    <acme:form-textbox code="authenticated.manager.task.form.link" path="link"/>
    <acme:form-checkbox code="authenticated.manager.task.form.publico" path="publico"/>
    <jstl:if test="${command == 'show'}">
    <acme:form-checkbox code="authenticated.manager.task.form.finished" path="finished"/>
    </jstl:if>
    
   

	<acme:form-submit test="${command == 'show'}" code="authenticated.manager.manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'show'}" code="authenticated.manager.manager.task.form.button.delete" action="/manager/task/delete"/>
	<acme:form-submit test="${command == 'create'}" code="authenticated.manager.manager.task.form.button.create" action="/manager/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.manager.manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'delete'}" code="authenticated.manager.manager.task.form.button.delete" action="/manager/task/delete"/>		
	<acme:form-return code="anonymous.task.form.button.return"/>

</acme:form>