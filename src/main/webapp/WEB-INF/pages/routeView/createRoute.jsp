<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create new route</title>
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/routeValid.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/routeView/add" commandName="route">
    <form:label path="title">Name</form:label>
    <form:input path="title"/>
    <input type="submit" value="Create"/>
</form:form>

<form action="${pageContext.request.contextPath}/routeView/addStation" method="POST">
    <label for="stationName">Select station</label>
    <select id="stationName" name="stationName" required/>
    <c:forEach items="${stationList}" var="station">
        <option>${station.name}</option>
    </c:forEach>
    </select>
    <input type="time" id="appearenceTime" name="appearenceTime" required/>
    <label for="appearenceTime">Appearence time</label>

    <input type="submit" value="Add station distance"/>
</form>

<h3>StationDistances</h3>
<c:if test="${!empty distanceList}">
    <table class="data">
        <tr>
            <th>Station name</th>
            <th>Appearence time</th>
        </tr>
        <c:forEach items="${distanceList}" var="stationDistanceDto">
            <tr>
                <td>${stationDistanceDto.stationName}</td>
                <td>${stationDistanceDto.appearenceTime}</td>
            </tr>
        </c:forEach>
        <form:form method="post" action="${pageContext.request.contextPath}/routeView/clearDistanceList" commandName="distanceList">
            <input type="submit" value="Clear distanceList"/>
        </form:form>
    </table>
    <c:if test="${msg != null}">
        <h4 style="color: red">
                ${msg}
        </h4>
    </c:if>
</c:if>
</body>
</html>
