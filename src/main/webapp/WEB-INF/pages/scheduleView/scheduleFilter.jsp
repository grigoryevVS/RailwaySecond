<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Buy ticket</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

<h2>Search train</h2>
<form:form method="post" action="${pageContext.request.contextPath}/scheduleView/filteredSchedule" commandName="filter">
    <form:label path="stationFromName">Departure station</form:label>
    <form:input path="stationFromName" id="stationFromName" />

    <form:label path="stationToName">Arrive station</form:label>
    <form:input path="stationToName" id="stationToName"/>

    <form:label path="date">Date</form:label>
    <form:input path="date" type="date"/>

    <input type="submit" value="Search" />
    <c:if test="${msg != null}">
        <div style="color: red">
                ${msg}
        </div>
    </c:if>
</form:form>
</body>
</html>
