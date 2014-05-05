<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Passengers on train</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="management"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <h3>Passengers of train ${schedule.train.name}</h3>
        <c:if test="${msgf != null}">
            <h4 class="error">
                    ${msgf}
            </h4>
        </c:if>
        <c:if test="${msgg != null}">
            <h4 class="validmsg">
                    ${msgg}
            </h4>
        </c:if>
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
                        <td>
                            <button class="btn-my btn-small btn-success"
                                    onclick="getModal('/RailWay/scheduleView/passengers/deletePassenger/${user.userId}/${schedule.scheduleId}/')">
                                delete
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>

