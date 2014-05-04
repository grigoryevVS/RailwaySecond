<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>update route routeId ${route.routeId}</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="routes"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/routeView/refresh"
                   commandName="route">
            <form:label path="title"><h4>Name</h4></form:label>
            <form:input cssClass="autocomplete-suggestion" path="title"/>
            <form:hidden path="routeId"/>
            <input class="btn-large btn-success" type="submit" value="Update"/>
            <c:if test="${msgf != null}">
                <h4 class="error">
                        ${msgf}
                </h4>
            </c:if>
            <c:if test="${msgg != null}">
                <h4 class="msg">
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

