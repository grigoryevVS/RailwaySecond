<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>User ${user.firstName} ${user.lastName} editor</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <div>
            <h2>User ${user.firstName} ${user.lastName}</h2>
            <form:form method="post" action="${pageContext.request.contextPath}/userView/updateUser" commandName="user">
                <form:label path="login">Login</form:label>
                <form:input path="login" readonly="true" />

                <form:label path="password">Password</form:label>
                <form:input path="password" type="password"/>

                <form:label path="firstName">First name</form:label>
                <form:input path="firstName" readonly="true"/>

                <form:label path="lastName">Last name</form:label>
                <form:input path="lastName" readonly="true"/>

                <form:label path="birthDate">Birth date</form:label>
                <form:input path="birthDate" readonly="true" type="date"/>

                <form:hidden path="userId"/>

                <input  class="btn btn-danger" type="submit" value="Change account data" />
                <c:if test="${message != null}">
                    <h6 style="color: red">${message}</h6>
                </c:if>
            </form:form>
            <c:if test="${!empty ticketList}">
                <a class="btn btn-info" href="<c:url value="/userView/tickets/${user.userId}" />">Tickets</a>
            </c:if>
        </div>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>