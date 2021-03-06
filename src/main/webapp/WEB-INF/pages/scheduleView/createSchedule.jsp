<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Create new schedule</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="management"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/scheduleView/add"
                   commandName="schedule">
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
            <input class="btn-large btn-success" type="submit" value="Create"/>
            <c:if test="${msgf != null}">
                <h4 class="error">
                        ${msgf}
                </h4>
            </c:if>
            <c:if test="${msgg != null}">
                <h4 class="validmsg">
                        ${msgg}
                </h4>
            </c:if>
        </form:form>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>
