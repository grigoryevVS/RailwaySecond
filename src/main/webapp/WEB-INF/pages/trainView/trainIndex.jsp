<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="trains" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <h3>Trains</h3>
        <c:if  test="${!empty trainList}">
            <table class="table table-bordered">
                <tr>
                    <th>Name</th>
                    <th>Capacity</th>
                </tr>
                <c:forEach items="${trainList}" var="train">
                    <tr>
                        <td>${train.name}</td>
                        <td>${train.numberOfSeats}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>