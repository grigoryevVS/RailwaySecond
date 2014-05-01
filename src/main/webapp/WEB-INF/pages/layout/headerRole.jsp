<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@include file="styles.jsp" %>
<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
            <%@ include file="/WEB-INF/pages/layout/headerAdmin.jsp" %>
        </sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_USER">
            <%@ include file="/WEB-INF/pages/layout/headerUser.jsp" %>
        </sec:authorize>

        <%--<h6>Welcome : ${pageContext.request.userPrincipal.name}--%>
    </c:when>
    <c:otherwise>
        <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
            <%@ include file="/WEB-INF/pages/layout/headerAnonymous.jsp" %>
        </sec:authorize>
    </c:otherwise>
</c:choose>
