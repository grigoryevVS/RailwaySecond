<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Filtered schedule</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <a class="btn btn-info" href="${pageContext.request.contextPath}/scheduleView/scheduleFilter">Back to filter</a>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <h3>Filtered schedule</h3>
        <c:if test="${msg != null}">
            <div style="color: red">
                    ${msg}
            </div>
        </c:if>
        <c:if test="${!empty scheduleList}">
            <table class="table table-bordered">
                <tr>
                    <th>Id</th>
                    <th>From</th>
                    <th>TimeFrom</th>
                    <th>To</th>
                    <th>TimeTo</th>
                    <th>Train</th>
                    <th>Date</th>
                    <th>Seats</th>
                    <th>Details</th>
                    <th>Buy</th>
                </tr>
                <c:forEach items="${scheduleList}" var="scheduler">
                    <tr>
                        <td>${scheduler.id}</td>
                        <td>${scheduler.stationFrom}</td>
                        <td>${scheduler.appearTimeFrom}</td>
                        <td>${scheduler.stationTo}</td>
                        <td>${scheduler.appearTimeTo}</td>
                        <td>${scheduler.trainName}</td>
                        <td>${scheduler.date}</td>
                        <td>${scheduler.emptySeats}</td>
                        <td><a class="btn-small btn-info" href="/RailWay/routeView/detailsFromSchedule/${scheduler.id}">details</a></td>
                        <td><a class="btn-small btn-success" href="buyTicket/${scheduler.id}">buy</a></td>
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