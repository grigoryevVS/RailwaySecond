<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>update route routeId ${route.routeId}</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">

        <div>
            <form:form method="post" action="${pageContext.request.contextPath}/routeView/refresh" commandName="route">
            <form:label path="title"><h4>Name</h4></form:label>
            <form:input cssClass="autocomplete-suggestion" path="title"/>
            <form:hidden path="routeId"/>
        </div>

        <a class="btn btn-info" href="${pageContext.request.contextPath}/routeView/routes">Back to the list</a>
        <input class="btn btn-warning" type="submit" value="Update"/>
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

