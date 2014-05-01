<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/trainValid.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Create new train</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
<form:form method="post" action="${pageContext.request.contextPath}/trainView/add" commandName="train">
    <form:label path="name">Name</form:label>
    <form:input path="name" />
    <form:label path="numberOfSeats">Capacity</form:label>
    <form:input path="numberOfSeats" onkeypress="return IsNumeric(event);" ondrop="return false;"/>
    <span id="error" style="color: Red; display: none">* Input digits (0 - 9)</span>
    <script type="text/javascript">
        var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        function IsNumeric(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
            document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
    </script>
    <input type="submit" value="Create" />
    <c:if test="${msg != null}">
        <div style="color: red">
                ${msg}
        </div>
    </c:if>
</form:form>
</body>
</html>

