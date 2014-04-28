<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/stationValid.js"></script>
    <title>Create new station</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/stationView/add" commandName="station">
    <form:label path="name">Name</form:label>
    <form:input path="name" />
    <input type="submit" value="Create" />
    <c:if test="${msg != null}">
        <div style="color: red">
                ${msg}
        </div>
    </c:if>
</form:form>
</body>
</html>