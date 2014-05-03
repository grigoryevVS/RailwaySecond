<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/stationValid.js"></script>
    <title>update station stationId ${station.stationId}</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/stationView/refresh" commandName="station">
            <form:label path="name"><h4>Name</h4></form:label>
            <form:input path="name" />
            <form:hidden path="stationId"/>
            <input class="btn-large btn-success" type="submit" value="Update" />
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
