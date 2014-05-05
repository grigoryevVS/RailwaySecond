<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="login" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="row-fluid">
        <div class="span6 offset3">

            <div class="area">
                <form:form class="form-horizontal" method="post" action="add" commandName="user">
                <div class="heading">

                    <div class="row-fluid">
                        <div class="span4">
                            <h4 class="form-heading">Sign Up</h4>
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
                        <form:input path="login" placeholder="Unique login" type="text"/>
                    </div>
                </div>

                <div class="control-group">
                    <form:label class="control-label" path="password">Password</form:label>

                    <div class="controls">
                        <form:input path="password" placeholder="Unique login" type="password"/>
                    </div>
                </div>

                <div class="control-group">
                    <form:label class="control-label" path="firstName">First Name</form:label>

                    <div class="controls">
                        <form:input path="firstName" placeholder="Your first name" type="text"/>
                    </div>
                </div>

                <div class="control-group">
                    <form:label class="control-label" path="lastName">Last Name</form:label>

                    <div class="controls">
                        <form:input path="lastName" placeholder="Your last name" type="text"/>
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
                        <input class="btn btn-success" type="submit" value="Sign up"/>
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
