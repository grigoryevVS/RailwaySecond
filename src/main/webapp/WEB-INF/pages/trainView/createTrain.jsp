<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Create new train</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="trains"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
    <div class="jumbotron">
        <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/trainView/add"
                   commandName="train">
            <form:label path="name"><h4>Name</h4></form:label>
            <form:input path="name"/>
            <form:label path="numberOfSeats"><h4>Capacity</h4></form:label>
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
            <input class="btn-large btn-success" type="submit" value="Create"/>
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
        </form:form>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>

