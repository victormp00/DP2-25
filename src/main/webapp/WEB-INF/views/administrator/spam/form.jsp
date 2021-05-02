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
	<acme:form-textbox code="administrator.spam.es" path="spamEn"/>
	<acme:form-textbox code="administrator.spam.en" path="spamEs"/>
		
	<acme:form-submit test="${command == 'show'}" code="administrator.spam.button.update" action="/administrator/spam/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.spam.button.delete" action="/administrator/spam/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.spam.button.create" action="/administrator/spam/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.spam.button.update" action="/administrator/spam/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.spam.button.delete" action="/administrator/spam/delete"/>	
  	<acme:form-return code="administrator.spam.button.return"/>
</acme:form>
