<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Route</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="timetable"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">

        <h4>Station distances</h4>
        <c:if test="${!empty stationDistances}">
            <table class="table table-bordered ">
                <tr>
                    <th>Sequence number</th>
                    <th>Station</th>
                    <th>Appearance time</th>
                </tr>
                <c:forEach items="${stationDistances}" var="stationDistanceDto">
                    <tr>
                        <td>${stationDistanceDto.sequenceNumber}</td>
                        <td>${stationDistanceDto.stationName}</td>
                        <td>${stationDistanceDto.appearenceTime}</td>
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
