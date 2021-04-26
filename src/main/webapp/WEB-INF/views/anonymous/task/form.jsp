
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

	<acme:form-return code="anonymous.task.form.button.return"/>

</acme:form>