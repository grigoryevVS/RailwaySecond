<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Buy ticket</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

        <%--<form class="form-horizontal" id="data" method="post" action="${pageContext.request.contextPath}/scheduleView/filteredSchedule" commandName="filter">--%>


        <%--</form>--%>


    <!-- Jumbotron -->
    <div class="filter" align="center" style="background-color: lightgrey">
        <h3>Search train</h3>
        <form:form cssClass="form-inline" id="data" method="post" action="${pageContext.request.contextPath}/scheduleView/filteredSchedule" commandName="filter">
            <form:label path="stationFromName">Departure station</form:label>
            <form:input path="stationFromName" id="stationFromName" />

            <form:label path="stationToName">Arrive station</form:label>
            <form:input path="stationToName" id="stationToName"/>

            <form:label path="date">Date</form:label>
            <form:input path="date" type="date"/>

            <input class="btn btn-success" type="submit" value="Search" onclick="getTimetable(); return false;" />
            <c:if test="${msg != null}">
                <h4 style="color: red">
                        ${msg}
                </h4>
            </c:if>
        </form:form>
    </div>

    <hr>

    <div id="table">

    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>
