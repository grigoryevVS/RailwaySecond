<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file = "/WEB-INF/pages/layout/headerStyles.jsp" %>
<table width="100%">
    <tr>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/userView/registration">Registration</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/userView/login">Authorization</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/stationView/stations">Station management</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/trainView/trains">Train management</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/routeView/routes">Route management</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/scheduleView/schedule">Schedule management</a></h4> </td>
    </tr>
</table>

<br/>
<div id="user-details">
    <c:if test="${authentication.getName() ne null}">
        <h2>Hello, ${authentication.getName()}!</h2>
        <c:if test="${authentication.getName() ne 'guest'}">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:if>
        <c:if test="${authentication.getName() eq 'guest'}">
            Please, <a href="${pageContext.request.contextPath}/userView/login">Login</a> or <a href="${pageContext.request.contextPath}/userView/registration">Registration</a>
        </c:if>
    </c:if>
</div>