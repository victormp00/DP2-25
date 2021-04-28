<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.manager.manager.form.label.company" path="company"/>
	<acme:form-textbox code="authenticated.manager.manager.form.label.sector" path="sector"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.manager.manager.form.button.create" action="/authenticated/manager/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.manager.manager.form.button.update" action="/authenticated/manager/update"/>
	<acme:form-return code="authenticated.manager.manager.form.button.return"/>
</acme:form>
