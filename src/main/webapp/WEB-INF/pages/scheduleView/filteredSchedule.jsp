<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <%@include file="../layout/modalBuy.jsp" %>
    <meta charset="utf-8">
    <title>Filtered schedule</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="timetable"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <c:if test="${!empty scheduleList}">
            <table class="table table-bordered">
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
                            <button class="btn-my btn-small btn-info"
                                    onclick="location.href='/RailWay/routeView/detailsFromSchedule/${scheduler.id}'">
                                details
                            </button>
                            <button class="btn-my btn-small btn-success"
                                    onclick="getModal('/RailWay/scheduleView/buyTicket/${scheduler.id}/')">buy ticket
                            </button>
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