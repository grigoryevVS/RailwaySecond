<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<h3>Routes</h3>
<c:if test="${!empty routeList}">
    <table>
        <tr>
            <th>RouteId</th>
            <th>Name</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${routeList}" var="route">
            <tr>
                <td>${route.routeId}</td>
                <td>${route.title}</td>
                <td><a href="details/${route.routeId}">details</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

