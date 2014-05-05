<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
</head>
<body>
<div class="container">
    <c:set var="activeMenu" value="routes"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
    <div class="jumbotron">
        <h4>Routes</h4>
        <c:if test="${msgf != null}">
            <h4 class="error">
                    ${msgf}
            </h4>
        </c:if>
        <c:if test="${msgg != null}">
            <h4 class="msg">
                    ${msgg}
            </h4>
        </c:if>
        <c:if test="${!empty routeList}">
            <table class="table table-bordered text-center">
                <tr>
                    <th>RouteId</th>
                    <th>Name</th>
                    <th>View details</th>
                </tr>
                <c:forEach items="${routeList}" var="route">
                    <tr>
                        <td>${route.routeId}</td>
                        <td>${route.title}</td>
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
