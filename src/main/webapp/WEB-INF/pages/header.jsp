<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table width="100%">
    <tr>
        <td align="center"><h2><a href="${pageContext.request.contextPath}/logInView/authorization">Authorization</a></h2> </td>
        <td align="center"><h2><a href="${pageContext.request.contextPath}/stationView/stations">Station management</a></h2> </td>
        <td align="center"><h2><a href="${pageContext.request.contextPath}/trainView/trains">Train management</a></h2> </td>
        <td align="center"><h2><a href="${pageContext.request.contextPath}/routeView/routes">Route management</a></h2> </td>
    </tr>
</table>
<br/>