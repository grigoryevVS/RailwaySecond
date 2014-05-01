<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Create new schedule</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/scheduleView/add" commandName="schedule">
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
    <label for="dateTrip">Date trip</label>
    <input type="date" id="dateTrip" name="dateTrip" required/>
    <input type="submit" value="Create"/>
    <c:if test="${msg != null}">
        <h4 style="color: red">
                ${msg}
        </h4>
    </c:if>
</form:form>
</body>
</html>
