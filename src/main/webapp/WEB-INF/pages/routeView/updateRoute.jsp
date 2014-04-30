<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>update route routeId ${route.routeId}</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/routeView/refresh" commandName="route">
    <form:label path="title">Name</form:label>
    <form:input path="title" />
    <form:hidden path="routeId"/>

    <input type="submit" value="Update" />
    <c:if test="${msg != null}">
        <h4 style="color: red">
                ${msg}
        </h4>
    </c:if>

</form:form>
</body>
</html>
