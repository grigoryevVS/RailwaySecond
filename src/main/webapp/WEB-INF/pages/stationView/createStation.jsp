<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create new station</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
    <div class="jumbotron">

        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/stationView/add" commandName="station">
            <form:label path="name"><h4>Name</h4></form:label>
            <form:input path="name" />
            <input class="btn-large btn-success" type="submit" value="Create" />
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