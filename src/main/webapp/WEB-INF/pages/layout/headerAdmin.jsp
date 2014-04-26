<%@include file = "/WEB-INF/pages/layout/headerStyles.jsp" %>
<table width="100%">
    <tr>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/stationView/stations">Station management</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/trainView/trains">Train management</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/routeView/routes">Route management</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/scheduleView/schedule">Schedule management</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/userView/editor/${pageContext.request.userPrincipal.name}">User editor</a></h4> </td>
    </tr>
</table>
