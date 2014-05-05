<div class="masthead">
    <div class="row-fluid">
        <div class="bug2">
            <h2 class="muted"><a href="${pageContext.request.contextPath}/index">SBB CFF FFS</a></h2>
            <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name != null}">
            <h6>Welcome : ${pageContext.request.userPrincipal.name}
                </c:when>
                </c:choose>
        </div>
        <div class="bug">
            <a href="${pageContext.request.contextPath}/index"><img src="/RailWay/images/logo.jpg"></a>
        </div>
    </div>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <ul class="nav">
                    <li <c:if test="${activeMenu eq 'home'}">class="active"</c:if> ><a  href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li <c:if test="${activeMenu eq 'stations'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/stationView/stations">Stations</a></li>
                    <li <c:if test="${activeMenu eq 'trains'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/trainView/trains">Trains</a></li>
                    <li <c:if test="${activeMenu eq 'routes'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/routeView/routes">Routes</a></li>
                    <li <c:if test="${activeMenu eq 'timetable'}">class="active"</c:if>  ><a href="${pageContext.request.contextPath}/scheduleView/scheduleFilter">Timetable</a></li>
                    <li <c:if test="${activeMenu eq 'management'}">class="active"</c:if> ><a href="${pageContext.request.contextPath}/scheduleView/schedule">Management</a></li>
                    <li <c:if test="${activeMenu eq 'editor'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/userView/editor/${pageContext.request.userPrincipal.name}">Account</a> </li>
                    <li <c:if test="${activeMenu eq 'logout'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/userView/logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>