<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ include file="/WEB-INF/pages/layout/header.jsp" %>

<form:form method="post" action="createRoute" commandName="route">
    <table>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add route"/>
            </td>
        </tr>
    </table>
</form:form>

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
                <td><a href="updateRoute/${route.routeId}">update</a></td>
                <td><a href="delete/${route.routeId}">delete</a></td>
                <td><a href="details/${route.routeId}">details</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

