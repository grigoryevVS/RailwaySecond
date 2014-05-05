<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Log in</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="login" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="row-fluid">
        <div class="span6 offset3">
            <div class="area">
                <form class="form-horizontal" method="POST" action="<c:url value="/j_spring_security_check" />">
                    <div class="heading">
                        <h4 class="form-heading">Sign In</h4>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="login">Login</label>

                        <div class="controls">
                            <input id="login" placeholder="Your login" type="text" name="j_username">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Password</label>

                        <div class="controls">
                            <input id="password" placeholder="Your password" type="password" name="j_password">
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <input class="btn btn-success" type="submit" value="login"/>
                            <a class="btn btn-success" href="${pageContext.request.contextPath}/userView/registration">Sign Up</a>
                        </div>
                    </div>
                </form>
            </div>
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