<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Filtered schedule</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="timetable" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <c:if test="${msg != null}">
            <h4 style="color: red">
                    ${msg}
            </h4>
        </c:if>
        <c:if test="${!empty scheduleList}">
            <table class="table table-bordered">
                <tr>
                    <th>Train</th>
                    <th>Route</th>
                    <th>From</th>
                    <th>Departure time</th>
                    <th>To</th>
                    <th>Arrival time</th>
                    <th>Date</th>
                    <th>Seats</th>
                    <th></th>
                </tr>
                <c:forEach items="${scheduleList}" var="scheduler">
                    <tr>
                        <td>${scheduler.trainName}</td>
                        <td>${scheduler.routeName}</td>
                        <td>${scheduler.stationFrom}</td>
                        <td>${scheduler.departureTime}</td>
                        <td>${scheduler.stationTo}</td>
                        <td>${scheduler.arrivalTime}</td>
                        <td>${scheduler.date}</td>
                        <td>${scheduler.emptySeats}</td>
                        <td>
                            <a class="btn-small btn-info" href="/RailWay/routeView/detailsFromSchedule/${scheduler.id}">details</a>
                            <a class="btn-small btn-success" href="buyTicket/${scheduler.id}">buy</a>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>