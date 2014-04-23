<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>
<%@include file="/WEB-INF/pages/layout/headerStyles.jsp" %>
<%@include file="/WEB-INF/pages/layout/header.jsp" %>
<%@include file="/WEB-INF/pages/layout/sidebar.jsp" %>
<table class=".msg">
    <tr>
        <td align="center">
            <h2>Congratulation!</h2>
            <span>You can login or stay anonymous user<a href="${pageContext.request.contextPath}/userView/login">Login</a></span>
        </td>
    </tr>
</table>
</body>
</html>