<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>update train trainId ${train.trainId}</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <form:form method="post" action="${pageContext.request.contextPath}/trainView/refresh" commandName="train">
            <form:label path="name">Name</form:label>
            <form:input path="name" />
            <form:label path="name">Capacity</form:label>
            <form:input path="numberOfSeats" />
            <form:hidden path="trainId"/>

            <input type="submit" value="Update" />
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
