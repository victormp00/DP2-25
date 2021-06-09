<%--
- list.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language= "java" %>
<%@taglib prefix= "jstl" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix= "acme" tagdir="/WEB-INF/tags" %>


<acme:list>
	<acme:list-column code="authenticated.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="authenticated.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="authenticated.shout.list.label.text" path="text" width="60%"/>
	
	<acme:list-column code="authenticated.xxx.xxxdate" path="xxx.xxxdate" width="15%"/>
	<acme:list-column code="authenticated.xxx.xxxmoment" path="xxx.xxxmoment" width="10%"/>
		<acme:list-column code="authenticated.xxx.xxxamount.amount" path="xxx.xxxamount.amount" width="10%"/>
	<acme:list-column code="authenticated.xxx.xxxamount.currency" path="xxx.xxxamount.currency" width="10%"/>
	<acme:list-column code="authenticated.xxx.xxxboolean" path="xxx.xxxboolean" width="5%"/>
</acme:list>
