<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create new train</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
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
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>

