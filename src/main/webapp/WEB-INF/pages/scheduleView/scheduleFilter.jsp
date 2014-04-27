<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scheduleFilter.js"></script>
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
</form:form>

<%--<label for="stationFromName">Select station from</label>--%>
<%--<select id="stationFromName" name="stationFromName" required/>--%>
<%--<c:forEach items="${stationListFrom}" var="station">--%>
<%--<option>${station.name}</option>--%>
<%--</c:forEach>--%>
<%--</select>--%>

<%--<label for="stationToName">Select station to</label>--%>
<%--<select id="stationToName" name="stationToName" required/>--%>
<%--<c:forEach items="${stationListTo}" var="station">--%>
<%--<option>${station.name}</option>--%>
<%--</c:forEach>--%>
<%--</select>--%>
</body>
</html>