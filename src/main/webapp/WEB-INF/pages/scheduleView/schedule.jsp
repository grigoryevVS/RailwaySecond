<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="createSchedule" commandName="schedule">
    <table class="table table-bordered text-center">
        <tr>
            <td colspan="2">
                <input type="submit" value="Add schedule"/>
            </td>
        </tr>
    </table>
</form:form>

<h4>Schedule</h4>
<c:if test="${msg != null}">
    <h4 style="color: red">
            ${msg}
    </h4>
</c:if>
<c:if test="${!empty scheduleList}">
    <table class="table table-bordered text-center" >
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
                <%@ include file="/WEB-INF/pages/layout/adminHrefSchedule.jsp" %>
                <td><a href="/RailWay/routeView/detailsFromSchedule/${scheduler.id}">Route details</a></td>
                <td><a href="buyTicket/${scheduler.id}">buy ticket</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

