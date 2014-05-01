<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Update schedule</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
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
            <input class="btn btn-danger" type="submit" value="Update"/>
            <c:if test="${msg != null}">
                <h4 style="color: red">
                        ${msg}
                </h4>
            </c:if>
        </form:form>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>