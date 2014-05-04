<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="routes" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">

        <form:form method="get" action="${pageContext.request.contextPath}/routeView/createRoute" commandName="route">
            <input class="btn btn-success" align="center" type="submit" value="Add route"/>
        </form:form>

        <h4>Routes</h4>
        <div>
        <c:if test="${msgg != null}">
            <h4 class="msg">
                    ${msgg}
            </h4>
        </c:if>
        <c:if test="${msgf != null}">
            <h4 class="error">
                    ${msgf}
            </h4>
        </c:if>
        </div>
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
                            <button class="btn-my btn-small btn-success" onclick="location.href='updateRoute/${route.routeId}'">update</button>
                            <button class="btn-my btn-small btn-success" onclick="getModal('/RailWay/routeView/delete/${route.routeId}/')">delete</button>
                            <button class="btn-my btn-small btn-info" onclick="location.href='details/${route.routeId}'">details</button>
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

