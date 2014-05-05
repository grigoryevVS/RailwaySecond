<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>update station stationId ${station.stationId}</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="stations"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/stationView/refresh"
                   commandName="station">
            <form:label path="name"><h4>Name</h4></form:label>
            <form:input path="name"/>
            <form:hidden path="stationId"/>
            <input class="btn-large btn-success" type="submit" value="Update"/>
            <c:if test="${msgg != null}">
                <h4 class="validmsg">
                        ${msgg}
                </h4>
            </c:if>
            <c:if test="${msgf != null}">
                <h4 class="error">
                        ${msgf}
                </h4>
            </c:if>
        </form:form>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>
