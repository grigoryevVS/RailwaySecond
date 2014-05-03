<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Passengers on train</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <h3>Passengers of train ${schedule.train.name}</h3>
        <c:if test="${!empty userList}">
            <table class="table table-bordered">
                <tr>
                    <th>User id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Birth date</th>
                    <th>Cancel trip</th>
                </tr>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.birthDate}</td>
                        <td><a class="btn-small btn-success" onclick="return isDelete()" href="deletePassenger/${user.userId}/${schedule.scheduleId}">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/scheduleView/schedule">Back to schedule</a>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>

