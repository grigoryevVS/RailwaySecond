<c:choose>
<c:when test="${pageContext.request.userPrincipal.name != null}">
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <div class="manage">
        <button class="btn-my btn-small btn-success"
                onclick="location.href='/RailWay/scheduleView/updateSchedule/${scheduler.id}'">
            update
        </button>
        <button class="btn-my btn-small btn-success" onclick="getModal('/RailWay/scheduleView/delete/${scheduler.id}/')">delete</button>
        </div>
        <div class="manage-low">
        <button class="btn-my btn-small btn-info"
                onclick="location.href='/RailWay/scheduleView/passengers/${scheduler.id}'">
            tickets
        </button>
        <button class="btn-my btn-small btn-info"
                onclick="location.href='/RailWay/routeView/detailsFromSchedule/${scheduler.id}'">
            details
        </button>
        </div>
    </sec:authorize>
</c:when>
</c:choose>