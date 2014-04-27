<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <title>User tickets</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<h5 align="center">User ${user.firstName} ${user.lastName} tickets</h5>
<c:if test="${!empty ticketList}">
    <table align="center">
        <tr>
            <th>Train</th>
            <th>Route</th>
            <th>Date trip</th>
            <th>Departure station</th>
            <th>Departure time</th>
            <th>Arriving station</th>
            <th>Arriving time</th>
        </tr>
        <c:forEach items="${ticketList}" var="ticketDto">
            <tr>
                <td>${ticketDto.trainName}</td>
                <td>${ticketDto.routeName}</td>
                <td>${ticketDto.date}</td>
                <td>${ticketDto.stationFrom}</td>
                <td>${ticketDto.appearTimeFrom}</td>
                <td>${ticketDto.stationTo}</td>
                <td>${ticketDto.appearTimeTo}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
