<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

<h4>Schedule</h4>
<c:if test="${msg != null}">
    <h4 style="color: red">
            ${msg}
    </h4>
</c:if>
<c:if test="${!empty scheduleList}">
    <table class="table table-bordered">
        <tr>
            <th>ScheduleId</th>
            <th>StationFrom</th>
            <th>AppearTimeFrom</th>
            <th>StationTo</th>
            <th>AppearTimeTo</th>
            <th>Train</th>
            <th>Date</th>
            <th>Route</th>
            <th>EmptySeats</th>
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
                <td>${scheduler.routeName}</td>
                <td>${scheduler.emptySeats}</td>
                <td><a href="buyTicket/${scheduler.id}">buy ticket</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

