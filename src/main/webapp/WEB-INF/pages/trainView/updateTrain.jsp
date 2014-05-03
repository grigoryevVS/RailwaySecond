<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>update train trainId ${train.trainId}</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="trains" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/trainView/refresh" commandName="train">
            <form:label path="name">Name</form:label>
            <form:input path="name" />
            <form:label path="name">Capacity</form:label>
            <form:input path="numberOfSeats" />
            <form:hidden path="trainId"/>

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
