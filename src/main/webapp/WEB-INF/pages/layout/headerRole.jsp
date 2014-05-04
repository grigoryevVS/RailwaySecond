<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/pages/layout/modal.jsp" %>
<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
            <%@ include file="/WEB-INF/pages/layout/headerAdmin.jsp" %>
        </sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_USER">
            <%@ include file="/WEB-INF/pages/layout/headerUser.jsp" %>
        </sec:authorize>
    </c:when>
    <c:otherwise>
        <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
            <%@ include file="/WEB-INF/pages/layout/headerAnonymous.jsp" %>
        </sec:authorize>
    </c:otherwise>
</c:choose>
