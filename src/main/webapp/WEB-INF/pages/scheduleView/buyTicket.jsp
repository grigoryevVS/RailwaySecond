<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Buy ticket</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="timetable"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <table class="table table-bordered">
            <tr>
                <td>
                    <h3>Ticket data</h3>
                </td>
            </tr>
            <tr>
                <td>
                    <c:if test="${!empty ticket}">
                        <table class="table table-bordered">
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
                            <tr>
                                <td>${ticket.trainName}</td>
                                <td>${ticket.routeName}</td>
                                <td>${ticket.date}</td>
                                <td>${ticket.stationFrom}</td>
                                <td>${ticket.departureTime}</td>
                                <td>${ticket.stationTo}</td>
                                <td>${ticket.arrivalTime}</td>
                            </tr>
                        </table>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>