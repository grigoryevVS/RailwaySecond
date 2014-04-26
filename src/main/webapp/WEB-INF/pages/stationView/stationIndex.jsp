<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

<h3>Stations</h3>
<c:if test="${!empty stationList}">
    <table>
        <tr>
            <th>StationId</th>
            <th>Name</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${stationList}" var="station">
            <tr>
                <td>${station.stationId}</td>
                <td>${station.name}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>