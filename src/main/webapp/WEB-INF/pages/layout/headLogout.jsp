<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h5>Welcome : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/index.jsp" />" > Logout</a></h5>
</c:if>
