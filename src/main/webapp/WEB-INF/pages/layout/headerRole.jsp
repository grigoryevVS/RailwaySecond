<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h5><a href="<c:url value="/index" />" >Home</a></h5>
<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
            <%@ include file="/WEB-INF/pages/layout/headerAdmin.jsp" %>

        </sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_USER">
            <%@ include file="/WEB-INF/pages/layout/header.jsp" %>
        </sec:authorize>

        <h6>Welcome : ${pageContext.request.userPrincipal.name}
            | <a href="${pageContext.request.contextPath}/userView/logout">Logout</a></h6>
    </c:when>
    <c:otherwise>
        <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
            <%@ include file="/WEB-INF/pages/layout/header.jsp" %>
        </sec:authorize>
        <h6><a href="${pageContext.request.contextPath}/userView/registration">Sign up</a></h6> |
        <h6><a href="${pageContext.request.contextPath}/userView/login">Log in</a></h6>
    </c:otherwise>
</c:choose>
