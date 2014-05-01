<%--<%@include file = "/WEB-INF/pages/layout/headerStyles.jsp" %>--%>
<%--<table width="100%">--%>
    <%--<tr>--%>
        <%--<td align="center"><h4><a href="${pageContext.request.contextPath}/stationView/stations">Station management</a></h4> </td>--%>
        <%--<td align="center"><h4><a href="${pageContext.request.contextPath}/trainView/trains">Train management</a></h4> </td>--%>
        <%--<td align="center"><h4><a href="${pageContext.request.contextPath}/routeView/routes">Route management</a></h4> </td>--%>
        <%--<td align="center"><h4><a href="${pageContext.request.contextPath}/scheduleView/schedule">Schedule management</a></h4> </td>--%>
        <%--<td align="center"><h4><a href="${pageContext.request.contextPath}/scheduleView/scheduleFilter">Schedule filter</a></h4> </td>--%>
        <%--<td align="center"><h4><a href="${pageContext.request.contextPath}/userView/editor/${pageContext.request.userPrincipal.name}">User editor</a></h4> </td>--%>
    <%--</tr>--%>
<%--</table>--%>
<div class="masthead">
    <div class="row">
        <div class="span3">
            <h3 class="muted"><a href="${pageContext.request.contextPath}/index">SBB CFF FFS</a></h3>
        </div>
        <div class="span3 offset6">
            <p></p>
            <a href="${pageContext.request.contextPath}/index"><img src="/RailWay/images/logo1.jpg"></a>
        </div>
    </div>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <ul class="nav">
                    <li class="active"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/stationView/stations">Stations</a></li>
                    <li><a href="${pageContext.request.contextPath}/trainView/trains">Trains</a></li>
                    <li><a href="${pageContext.request.contextPath}/routeView/routes">Routes</a></li>
                    <li><a href="${pageContext.request.contextPath}/scheduleView/schedule">Schedule</a></li>
                    <li><a href="${pageContext.request.contextPath}/userView/editor/${pageContext.request.userPrincipal.name}">Account</a> </li>
                    <li><a href="${pageContext.request.contextPath}/userView/logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>