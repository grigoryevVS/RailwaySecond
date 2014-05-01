<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create new station</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <a class="btn-large btn-info" href="${pageContext.request.contextPath}/stationView/stations">Back to stations</a>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <div>
        <form:form method="post" action="${pageContext.request.contextPath}/stationView/add" commandName="station">
            <form:label path="name"><h4>Name</h4></form:label>
            <form:input path="name" />
        </div>
            <input class="btn btn-success" type="submit" value="Create" />
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