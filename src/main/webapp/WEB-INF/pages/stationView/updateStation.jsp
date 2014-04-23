<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>update station stationId ${station.stationId}</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/stationView/refresh" commandName="station">
    <form:label path="name">Name</form:label>
    <form:input path="name" />
    <form:hidden path="stationId"/>

    <input type="submit" value="Update" />

</form:form>
</body>
</html>
