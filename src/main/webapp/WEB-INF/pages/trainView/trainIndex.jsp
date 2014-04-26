<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

<h3>Trains</h3>
<c:if  test="${!empty trainList}">
    <table>
        <tr>
            <th>TrainId</th>
            <th>Name</th>
            <th>Capacity</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${trainList}" var="train">
            <tr>
                <td>${train.trainId}</td>
                <td>${train.name}</td>
                <td>${train.numberOfSeats}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>