<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ include file="/WEB-INF/pages/layout/header.jsp" %>
<%@ include file="/WEB-INF/pages/layout/sidebar.jsp" %>

<form:form method="post" action="createStation" commandName="station">

    <table>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add station"/>
            </td>
        </tr>
    </table>
</form:form>


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
                <td><a href="updateStation/${station.stationId}">update</a></td>
                <td><a href="delete/${station.stationId}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>