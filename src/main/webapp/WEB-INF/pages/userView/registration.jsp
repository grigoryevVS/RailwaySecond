<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <%--<%@ include file="/WEB-INF/pages/layout/header.jsp" %>--%>
    <%@ include file="/WEB-INF/pages/layout/header.jsp" %>
    <title>Registration</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headLogout.jsp" %>
<form:form method="post" action="add" commandName="user">
    <form:label path="login">Login</form:label>
    <form:input path="login" />

    <form:label path="password">Password</form:label>
    <form:input path="password" />

    <form:label path="firstName">FirstName</form:label>
    <form:input path="firstName" />

    <form:label path="lastName">LastName</form:label>
    <form:input path="lastName" />

    <form:label path="birthDate">BirthDate</form:label>
    <form:input path="birthDate" type="date"/>

    <input type="submit" value="Save" />

</form:form>
</body>
</html>