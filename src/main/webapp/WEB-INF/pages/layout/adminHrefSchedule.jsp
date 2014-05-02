<c:choose>
<c:when test="${pageContext.request.userPrincipal.name != null}">
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <td><a class="btn-small btn-success"  href="updateSchedule/${scheduler.id}">update</a></td>
        <td><a class="btn-small btn-success" href="delete/${scheduler.id}">delete</a></td>
        <td><a class="btn-small btn-info" href="passengers/${scheduler.id}">tickets</a></td>
    </sec:authorize>
</c:when>
</c:choose>