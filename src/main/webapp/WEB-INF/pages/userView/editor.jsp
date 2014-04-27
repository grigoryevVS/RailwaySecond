<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/registrationValid.js"></script>
    <title>User ${user.firstName} ${user.lastName} editor</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<h2>User ${user.firstName} ${user.lastName}</h2>
<form:form method="post" action="${pageContext.request.contextPath}/userView/updateUser" commandName="user">
    <form:label path="login">Login</form:label>
    <form:input path="login" readonly="true" />

    <form:label path="password">Password</form:label>
    <form:input path="password" type="password"/>

    <form:label path="firstName">First name</form:label>
    <form:input path="firstName" />

    <form:label path="lastName">Last name</form:label>
    <form:input path="lastName" />

    <form:label path="birthDate">Birth date</form:label>
    <form:input path="birthDate" type="date"/>

    <form:hidden path="userId"/>

    <input type="submit" value="Change account data" />

</form:form>
<c:if test="${!empty ticketList}">
<a href="<c:url value="/userView/tickets/${user.userId}" />">Tickets</a>
</c:if>

</body>
</html>
