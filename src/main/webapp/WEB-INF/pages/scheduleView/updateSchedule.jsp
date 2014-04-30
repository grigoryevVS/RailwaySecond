<html>
<head>
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Update schedule</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/scheduleView/refresh" commandName="schedule">
    <label for="trainName">Select train</label>
    <select id="trainName" name="trainName" required/>
    <c:forEach items="${trainList}" var="train">
        <option>${train.name}</option>
    </c:forEach>
    </select>
    <label for="routeName">Select route</label>
    <select id="routeName" name="routeName" required/>
    <c:forEach items="${routeList}" var="route">
        <option>${route.title}</option>
    </c:forEach>
    </select>
    <form:hidden path="scheduleId"/>
    <label for="dateTrip">Date trip</label>
    <input type="date" id="dateTrip" name="dateTrip" required/>
    <input type="submit" value="Update"/>
    <c:if test="${msg != null}">
        <h4 style="color: red">
                ${msg}
        </h4>
    </c:if>
</form:form>
</body>
</html>
