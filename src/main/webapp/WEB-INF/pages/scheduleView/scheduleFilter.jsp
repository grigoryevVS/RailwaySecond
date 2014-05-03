<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Buy ticket</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="timetable" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

        <%--<form class="form-horizontal" id="data" method="post" action="${pageContext.request.contextPath}/scheduleView/filteredSchedule" commandName="filter">--%>


        <%--</form>--%>


    <!-- Jumbotron -->
    <div class="filter" align="center" style="background-color: lightgrey">
        <h3>Search train</h3>
        <c:if test="${msg != null}">
            <h4 style="color: red">
                    ${msg}
            </h4>
        </c:if>
        <form:form cssClass="form-inline" id="data" method="post" action="${pageContext.request.contextPath}/scheduleView/filteredSchedule" commandName="filter">
            <form:label path="stationFromName">Departure station</form:label>
            <form:input path="stationFromName" id="stationFromName" />

            <form:label path="stationToName">Arrive station</form:label>
            <form:input path="stationToName" id="stationToName"/>

            <form:label path="date">Date</form:label>
            <form:input path="date" type="date"/>

            <input class="btn btn-success" type="submit" value="Search" onclick="getTimetable(); return false;" />
        </form:form>
    </div>

    <hr>

    <div id="table">
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
