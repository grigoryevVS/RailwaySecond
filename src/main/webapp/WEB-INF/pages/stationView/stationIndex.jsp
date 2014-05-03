<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Stations list</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="stations" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <h3>Stations</h3>
        <c:if test="${!empty stationList}">
            <table class="table table-bordered">
                <tr>
                    <th>Name</th>
                </tr>
                <c:forEach items="${stationList}" var="station">
                    <tr>
                        <td>${station.name}</td>
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



<%--<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>