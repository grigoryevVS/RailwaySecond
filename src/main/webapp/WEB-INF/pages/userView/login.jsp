<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/registrationValid.js"></script>
    <title>Log in</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<h2>Authentication</h2>
<form method="POST" action="<c:url value="/j_spring_security_check" />">
    <table id="login-box">
        <tr>
            <td align="right">login</td>
            <td><input id="login" type="text" name="j_username"/></td>
        </tr>
        <tr>
            <td align="right">password</td>
            <td><input id="password" type="password" name="j_password"/></td>
        </tr>
        <tr>
            <td align="right">Remember</td>
            <td><input type="checkbox" name="_spring_security_remember_me"/></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="login"/>
                <input type="reset" value="Reset"/></td>
        </tr>
    </table>
</form>
<c:if test="${msg != null}">
    <div>
            ${msg}
    </div>
</c:if>
</body>
</html>