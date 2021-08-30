<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox placeholder="http://example.com" code="anonymous.shout.form.label.info" path="info"/>
	
	<!-- parte Maolet -->
	<acme:form-textbox placeholder="^\w{2,4}:yy:mmdd$" code="anonymous.maolet.tiplet" path="maolet.tiplet"/>
	<acme:form-money code="anonymous.maolet.budget" path="maolet.budget"/>
	<acme:form-checkbox code="anonymous.maolet.important" path="maolet.important"/>
	
	<acme:form-submit test="${command == 'create'}" code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
