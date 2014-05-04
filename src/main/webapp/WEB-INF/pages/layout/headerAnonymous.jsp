<div class="masthead">
    <div class="row-fluid">
        <div class="bug2">
            <h2 class="muted"><a href="${pageContext.request.contextPath}/index">SBB CFF FFS</a></h2>
        </div>
        <div class="bug">
            <a href="${pageContext.request.contextPath}/index"><img src="/RailWay/images/logo1.jpg"></a>
        </div>
    </div>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <ul class="nav">
                    <li <c:if test="${activeMenu eq 'home'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li <c:if test="${activeMenu eq 'stations'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/stationView/stationIndex">Stations</a></li>
                    <li <c:if test="${activeMenu eq 'timetable'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/scheduleView/scheduleFilter">Timetable</a></li>
                    <li <c:if test="${activeMenu eq 'login'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/userView/login">Sign in</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>