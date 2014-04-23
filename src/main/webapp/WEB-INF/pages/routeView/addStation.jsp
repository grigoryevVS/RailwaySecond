<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Create new train</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/routeView/addStation" commandName="route">
    <form:label path="name">Station name</form:label>
    <select>
        <c:forEach items="${stationList}" var="station">
            <option>${station.name}</option>
        </c:forEach>
    </select>
    <form:label path="appearTime">Appearance time</form:label>
    <form:input path="appearTime" />
    <input type="submit" value="Create" />
</form:form>
</body>
</html>


