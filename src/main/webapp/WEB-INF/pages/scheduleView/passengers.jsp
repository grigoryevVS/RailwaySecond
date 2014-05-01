<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Passengers on train</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<h3>Passengers of train ${schedule.train.name}</h3>
<c:if test="${!empty userList}">
    <table>
        <tr>
            <th>User id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Birth date</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.userId}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.birthDate}</td>
                <td><a href="deletePassenger/${user.userId}/${schedule.scheduleId}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a align="center" href="${pageContext.request.contextPath}/scheduleView/schedule">Back to schedule</a>
</body>
</html>
