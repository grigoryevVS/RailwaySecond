<%@include file = "/WEB-INF/pages/layout/headerStyles.jsp" %>
<table width="100%">
    <tr>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/stationView/stationIndex">Station view</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/trainView/trainIndex">Train view</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/routeView/routeIndex">Route view</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/scheduleView/scheduleIndex">Schedule view</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/scheduleView/scheduleFilter">Schedule filter</a></h4> </td>
        <td align="center"><h4><a href="${pageContext.request.contextPath}/userView/editor/${pageContext.request.userPrincipal.name}">User editor</a></h4> </td>
    </tr>
</table>

<%--<br/>--%>
<%--<div id="user-details">--%>
    <%--<c:if test="${authentication.getName() ne null}">--%>
        <%--<h2>Hello, ${authentication.getName()}!</h2>--%>
        <%--<c:if test="${authentication.getName() ne 'guest'}">--%>
            <%--<a href="${pageContext.request.contextPath}/logout">Logout</a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${authentication.getName() eq 'guest'}">--%>
            <%--Please, <a href="${pageContext.request.contextPath}/userView/login">Login</a> or <a href="${pageContext.request.contextPath}/userView/registration">Registration</a>--%>
        <%--</c:if>--%>
    <%--</c:if>--%>
<%--</div>--%>