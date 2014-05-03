<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>update route routeId ${route.routeId}</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

        <a class="btn-large btn-info" href="${pageContext.request.contextPath}/routeView/routes">Back to route</a>

    <div class="jumbotron">
            <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/routeView/refresh" commandName="route">
            <form:label path="title"><h4>Name</h4></form:label>
            <form:input cssClass="autocomplete-suggestion" path="title"/>
            <form:hidden path="routeId"/>
        <input class="btn-large btn-success" type="submit" value="Update"/>
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

