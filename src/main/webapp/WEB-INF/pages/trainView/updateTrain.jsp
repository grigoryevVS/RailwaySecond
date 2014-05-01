<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/trainValid.js"></script>
    <title>update train trainId ${train.trainId}</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/trainView/refresh" commandName="train">
    <form:label path="name">Name</form:label>
    <form:input path="name" />
    <form:label path="name">Capacity</form:label>
    <form:input path="numberOfSeats" />
    <form:hidden path="trainId"/>

    <input type="submit" value="Update" />
    <c:if test="${msg != null}">
        <div style="color: red">
                ${msg}
        </div>
    </c:if>

</form:form>
</body>
</html>
