<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Create new station</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="stations" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
    <div class="jumbotron">

        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/stationView/add" commandName="station">
            <form:label path="name"><h4>Name</h4></form:label>
            <form:input path="name" />
            <input class="btn-large btn-success" type="submit" value="Create" />
        </form:form>
        <c:if test="${msgg != null}">
            <div class="validmsg">
                    ${msgg}
            </div>
        </c:if>
        <c:if test="${msgf != null}">
            <div class="error">
                    ${msgf}
            </div>
        </c:if>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>