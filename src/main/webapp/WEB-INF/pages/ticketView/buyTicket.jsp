<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <title>Buy ticket</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<h2>Buy ticket</h2>

<h2>Check ticket data</h2>
<c:if test="${!empty ticket}">
    <h3>Information about schedule</h3>
    <table class="data">
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

    <h3>Information about passenger</h3>
    <table class="data">
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

<input type="submit" value="Confirm"/>

</body>
</html>