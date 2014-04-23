<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create new route</title>
    <%@ include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
</head>
<%--<body>--%>
<%--<form:form method="post" action="${pageContext.request.contextPath}/routeView/add" commandName="route">--%>
    <%--<form:label path="title">Name</form:label>--%>
    <%--<form:input path="title" />--%>



    <%--<input type="submit" value="Create" />--%>
<%--</form:form>--%>
<%--</body>--%>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/routeView/add" commandName="route">
    <form:label path="title">Name</form:label>
    <form:input path="title" />
    <input type="submit" value="Create" />
</form:form>

<form:form method="post" action="${pageContext.request.contextPath}/routeView/addStation" commandName="route">
    <input type="submit" value="Add station distance" />
</form:form>

<h3>StationDistances</h3>
<c:if test="${!empty distanceList}">
    <table class="data">
        <tr>
            <th>Route name</th>
            <th>Station name</th>
            <th>Appearence time</th>
        </tr>
        <c:forEach items="${distanceList}" var="stationDistanceDto">
            <tr>
                <td>${stationDistanceDto.routeName}</td>
                <td>${stationDistanceDto.stationName}</td>
                <td>${stationDistanceDto.appearenceTime}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
