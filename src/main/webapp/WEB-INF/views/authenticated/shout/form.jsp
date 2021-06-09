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
	<acme:form-textbox code="authenticated.shout.form.label.author" path="author"/>
	<acme:form-textarea code="authenticated.shout.form.label.text" path="text"/>
	<acme:form-textbox placeholder="http://example.com" code="authenticated.shout.form.label.info" path="info"/>
	
	<!-- parte xxx -->
	<acme:form-textbox placeholder="dd/mm/yyyy/example " code="authenticated.xxx.xxxdate" path="xxx.xxxdate"/>
	<acme:form-money code="authenticated.xxx.xxxamount" path="xxx.xxxamount"/>
	<acme:form-checkbox code="authenticated.xxx.xxxboolean" path="xxx.xxxboolean"/>
	
	<acme:form-submit code="authenticated.shout.form.button.create" action="/authenticated/shout/create"/>
	<acme:form-return code="authenticated.shout.form.button.return"/>
</acme:form>
