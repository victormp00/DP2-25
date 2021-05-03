<!-- Indica que este fichero tiene una vista y que el lenguaje es JAVA -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Biblioteca de etiquetas(acme, acme:form-textbox,textare,submit,return), elementales, JSTL -->
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<!-- Campos de edicion, recordar la internacionalizacion con code -->
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
 	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout..form.label.info" path="info"/>
	
	<!-- action: a que endpoint quiero mandar lo capturado -->
 	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
 	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>