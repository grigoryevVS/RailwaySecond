<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Create new train</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/trainView/add" commandName="train">
    <form:label path="name">Name</form:label>
    <form:input path="name" />
    <form:label path="numberOfSeats">Capacity</form:label>
    <form:input path="numberOfSeats" />
    <input type="submit" value="Create" />
</form:form>
</body>
</html>
