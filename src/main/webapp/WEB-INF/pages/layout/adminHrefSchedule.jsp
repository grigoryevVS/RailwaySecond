<c:choose>
<c:when test="${pageContext.request.userPrincipal.name != null}">
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a class="btn-small btn-success"  href="updateSchedule/${scheduler.id}">update</a>
        <a class="btn-small btn-success" onclick="return isDelete()" href="delete/${scheduler.id}">delete</a>
        <a class="btn-small btn-info" href="passengers/${scheduler.id}">tickets</a>
    </sec:authorize>
</c:when>
</c:choose>