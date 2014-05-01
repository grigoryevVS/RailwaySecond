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
            <input class="btn btn-large btn-danger" align="center" type="submit" value="Add route"/>
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
                    <th>RouteId</th>
                    <th>Name</th>
                    <th>Update route</th>
                    <th>Delete route</th>
                    <th>View details</th>
                </tr>
                <c:forEach items="${routeList}" var="route">
                    <tr>
                        <td>${route.routeId}</td>
                        <td>${route.title}</td>
                        <td><a class="btn-small btn-danger" href="updateRoute/${route.routeId}"> update </a></td>
                        <td><a class="btn-small btn-danger" href="delete/${route.routeId}"> delete </a></td>
                        <td><a class="btn-small btn-info" href="details/${route.routeId}">details</a></td>
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

