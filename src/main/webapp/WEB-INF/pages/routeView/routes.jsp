<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">

        <form:form method="get" action="${pageContext.request.contextPath}/routeView/createRoute" commandName="route">
            <input class="btn btn-large btn-success" align="center" type="submit" value="Add route"/>
        </form:form>

        <h4>Routes</h4>
        <c:if test="${msg != null}">
            <h4 style="color: red">
                    ${msg}
            </h4>
        </c:if>
        <c:if test="${!empty routeList}">
            <table class="table table-bordered text-center">
                <tr>
                    <th>Name</th>
                    <th>Management</th>
                </tr>
                <c:forEach items="${routeList}" var="route">
                    <tr>
                        <td>${route.title}</td>
                        <td>
                            <a class="btn-my btn-small btn-success" href="updateRoute/${route.routeId}"> update </a>
                            <a class="btn-my btn-small btn-success" onclick="return isDelete()" href="delete/${route.routeId}"> delete </a>
                            <a class="btn-my btn-small btn-info" href="details/${route.routeId}">details</a>
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

