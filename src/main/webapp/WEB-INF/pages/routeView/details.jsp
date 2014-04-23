<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Route ${route.routeId}</title>
</head>
<body>
<h2>Route ${route.routeId}</h2>
<h3>Station distances</h3>
<c:if test="${!empty stationDistances}">
    <table class="data">
        <tr>
            <th>Sequence number</th>
            <th>Station</th>
            <th>Appearance time</th>
        </tr>
        <c:forEach items="${stationDistances}" var="stationDistanceDto">
            <tr>
                <td>${stationDistanceDto.sequenceNumber}</td>
                <td>${stationDistanceDto.stationName}</td>
                <td>${stationDistanceDto.appearenceTime}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>