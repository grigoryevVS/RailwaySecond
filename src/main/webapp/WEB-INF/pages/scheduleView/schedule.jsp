<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Full timetable view</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="management" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <form:form method="post" action="createSchedule" commandName="schedule">
            <input class="btn btn-success" type="submit" value="Add timetable"/>
        </form:form>

        <h4>Timetable</h4>
        <c:if test="${msgg != null}">
            <h4 class="validmsg">
                    ${msgg}
            </h4>
        </c:if>
        <c:if test="${msgf != null}">
            <h4 class="error">
                    ${msgf}
            </h4>
        </c:if>
        <c:if test="${!empty scheduleList}">
            <table class="table table-bordered text-center">
                <tr>
                    <th>Train</th>
                    <th>Route</th>
                    <th>From</th>
                    <th>Departure time</th>
                    <th>To</th>
                    <th>Arrival time</th>
                    <th>Date trip</th>
                    <th>Seats</th>
                    <th>Management</th>
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
                            <%@ include file="/WEB-INF/pages/layout/adminHrefSchedule.jsp" %>
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

