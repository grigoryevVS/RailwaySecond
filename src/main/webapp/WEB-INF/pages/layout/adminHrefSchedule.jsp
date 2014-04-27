<c:choose>
<c:when test="${pageContext.request.userPrincipal.name != null}">
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <td><a href="updateSchedule/${scheduler.id}">update</a></td>
        <td><a href="delete/${scheduler.id}">delete</a></td>
        <td><a href="passengers/${scheduler.id}">passengers</a></td>
        <td><a href="buyTicket/${scheduler.id}">buy ticket</a></td>
    </sec:authorize>
</c:when>
</c:choose>