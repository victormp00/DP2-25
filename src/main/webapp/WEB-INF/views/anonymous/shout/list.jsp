<!-- Indica que este fichero tiene una vista y que el lenguaje es JAVA -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Biblioteca de etiquetas(acme, acme:list-column), elementales, JSTL -->
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- Vista para el listado, solo de lectura con readonly="true"-->
<acme:list readonly="true">
	<!-- Cada columna tiene 3 cosas: código de internaciolizacion(cabecera de la columna), 
	que atributo quiero mostrar con path y la ultima columna es para determinar el ancho 
	de la columna. -->
 	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
 	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="60%"/>
</acme:list>