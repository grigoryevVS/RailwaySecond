<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/stationValid.js"></script>
    <title>update station stationId ${station.stationId}</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/stationView/refresh" commandName="station">
    <form:label path="name">Name</form:label>
    <form:input path="name" />
    <form:hidden path="stationId"/>

    <input type="submit" value="Update" />
    <c:if test="${msg != null}">
        <div style="color: red">
                ${msg}
        </div>
    </c:if>

</form:form>
</body>
</html>
