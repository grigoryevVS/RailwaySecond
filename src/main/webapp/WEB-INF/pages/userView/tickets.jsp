<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>User tickets</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="editor"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">

        <div>
            <h5 align="center">User ${user.firstName} ${user.lastName} tickets</h5>
            <c:if test="${!empty ticketList}">
                <table class="table table-bordered">
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
                            <td>${ticketDto.departureTime}</td>
                            <td>${ticketDto.stationTo}</td>
                            <td>${ticketDto.arrivalTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>