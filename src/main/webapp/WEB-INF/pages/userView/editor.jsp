<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>User ${user.firstName} ${user.lastName} editor</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="editor" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="row-fluid">
        <div class="span6 offset3">
            <div class="area">
                <form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/userView/updateUser" commandName="user">
                    <input type="hidden" name="userId" value="${user.userId}"/>
                    <input type="hidden" name="role" value="${user.role}"/>
                    <div class="heading">

                    <div class="row">
                        <div class="span10 offset2">
                            <div>
                            <h2 class="form-heading">User ${user.firstName} ${user.lastName}</h2>
                            </div>
                        </div>
                        <div class="span4">
                            <c:if test="${msg != null}">
                                <div class="error">
                                        ${msg}
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

                    <div class="control-group">
                    <form:label class="control-label" path="login">Login</form:label>

                    <div class="controls">
                        <form:input path="login" readonly="true" type="text"/>
                    </div>
                </div>

                    <div class="control-group">
                        <form:label class="control-label" path="password">Password</form:label>

                        <div class="controls">
                            <form:input path="password" type="password"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label class="control-label" path="firstName">First Name</form:label>

                        <div class="controls">
                            <form:input path="firstName" type="text"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label class="control-label" path="lastName">Last Name</form:label>

                        <div class="controls">
                            <form:input path="lastName" type="text"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <form:label class="control-label" path="birthDate">Birth Date</form:label>

                        <div class="controls">
                            <form:input path="birthDate" type="date"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <input  class="btn btn-success" type="submit" value="Change account" />
                            <c:if test="${!empty ticketList}">
                                <a class="btn btn-info" href="<c:url value="/userView/tickets/${user.userId}" />">Tickets</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </form:form>
            <c:if test="${msgg != null}">
                <div class="validmsg">
                        ${msgg}
                </div>
            </c:if>
            <c:if test="${msgf != null}">
                <div class="error">
                        ${msgf}
                </div>
            </c:if>
        </div>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>