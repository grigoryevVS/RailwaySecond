
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Route</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div>
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name != null}">
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                    <a class="btn btn-info" href="/RailWay/scheduleView/schedule">Back to full schedule</a>
                </sec:authorize>
                <sec:authorize ifAnyGranted="ROLE_USER">
                    <a class="btn btn-info" href="/RailWay/scheduleView/scheduleIndex">Back to full schedule</a>
                </sec:authorize>
            </c:when>
            <c:otherwise>
                <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                    <a class="btn btn-info" href="/RailWay/scheduleView/scheduleIndex">Back to full schedule</a>
                </sec:authorize>
            </c:otherwise>
        </c:choose>

    </div>

    <!-- Jumbotron -->
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
