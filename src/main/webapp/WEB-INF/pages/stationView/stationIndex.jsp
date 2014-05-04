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
                    <th>Name <h6>*(You can just click on the station, to see its timetable)</h6></th>
                </tr>
                <c:forEach items="${stationList}" var="station">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/stationView/stationFilter/${station.name}">${station.name}</a></td>
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