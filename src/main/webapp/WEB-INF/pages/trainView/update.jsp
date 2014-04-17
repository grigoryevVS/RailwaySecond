<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>update train trainId ${train.trainId}</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/trainView/refresh" commandName="train">
    <form:label path="name">Name</form:label>
    <form:input path="name" />
    <form:label path="name">Capacity</form:label>
    <form:input path="numberOfSeats" />
    <form:hidden path="trainId"/>

    <input type="submit" value="Update" />

</form:form>
</body>
</html>
