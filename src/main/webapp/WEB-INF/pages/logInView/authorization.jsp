<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ include file="../header.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Authorization employee</title>
    <script type="text/javascript" src="authorizationJS.js"></script>
</head>
<body>
<form id="frm" action="" onsubmit="return checkForm()">
    <label for="login">Login:</label>
    <input id="login" name="firstName" value=""><br/>
    <label for="password">Password:</label>
    <input id="password" name="password" value=""><br/>
    <input type="submit" name="logIn" value="Log in" />
</form>
</body>

