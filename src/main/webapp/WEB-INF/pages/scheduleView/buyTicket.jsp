<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Buy ticket</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<h4 align="center">Congratulation</h4>

<h5 align="center">Ticket data</h5>
<c:if test="${!empty ticket}">
    <h6 align="center">Information about schedule</h6>
    <table class="data" align="center">
        <tr>
            <th>Train</th>
            <th>Route</th>
            <th>Date trip</th>
            <th>Departure station</th>
            <th>Departure time</th>
            <th>Arriving station</th>
            <th>Arriving time</th>
        </tr>
        <tr>
            <td>${ticket.trainName}</td>
            <td>${ticket.routeName}</td>
            <td>${ticket.date}</td>
            <td>${ticket.stationFrom}</td>
            <td>${ticket.appearTimeFrom}</td>
            <td>${ticket.stationTo}</td>
            <td>${ticket.appearTimeTo}</td>
        </tr>
    </table>

    <h6 align="center">Information about passenger</h6>
    <table class="data" align="center">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Birth date</th>
        </tr>
        <tr>
            <td>${ticket.firstName}</td>
            <td>${ticket.lastName}</td>
            <td>${ticket.birthDate}</td>
        </tr>
    </table>
</c:if>

<input type="submit" value="Confirm" />
<a align="center" href="${pageContext.request.contextPath}/scheduleView/scheduleIndex">Back to schedule</a>
</body>
</html>