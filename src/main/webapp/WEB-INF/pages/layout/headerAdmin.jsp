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
                    <li><a href="${pageContext.request.contextPath}/scheduleView/scheduleIndex">Full timetable</a></li>
                    <li><a href="${pageContext.request.contextPath}/scheduleView/schedule">Manage</a></li>
                    <li><a href="${pageContext.request.contextPath}/userView/editor/${pageContext.request.userPrincipal.name}">Account</a> </li>
                    <li><a href="${pageContext.request.contextPath}/userView/logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>