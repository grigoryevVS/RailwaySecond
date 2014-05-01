<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Buy ticket</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <h2>Search train</h2>
        <form:form method="post" action="${pageContext.request.contextPath}/scheduleView/filteredSchedule" commandName="filter">
            <form:label path="stationFromName">Departure station</form:label>
            <form:input path="stationFromName" id="stationFromName" />

            <form:label path="stationToName">Arrive station</form:label>
            <form:input path="stationToName" id="stationToName"/>

            <form:label path="date">Date</form:label>
            <form:input path="date" type="date"/>

            <input class="btn btn-success" type="submit" value="Search" />
            <c:if test="${msg != null}">
                <div style="color: red">
                        ${msg}
                </div>
            </c:if>
        </form:form>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>
