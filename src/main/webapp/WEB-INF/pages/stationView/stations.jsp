<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Stations management</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="stations"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <form:form method="post" action="createStation" commandName="station">
            <input class="btn btn-success" type="submit" value="Add station"/>
        </form:form>

        <h4>Stations</h4>
        <c:if test="${msgg != null}">
            <h4 class="validmsg">
                    ${msgg}
            </h4>
        </c:if>
        <c:if test="${msgf != null}">
            <h4 class="error">
                    ${msgf}
            </h4>
        </c:if>
        <c:if test="${!empty stationList}">
            <table class="table table-bordered">
                <tr>
                    <th>Name <h6>*(You can just click on the station, to see its timetable)</h6></th>
                    <th>Management</th>
                </tr>
                <c:forEach items="${stationList}" var="station">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/stationView/stationFilter/${station.name}">${station.name}</a>
                        </td>
                        <td>
                            <button class="btn-my btn-small btn-success"
                                    onclick="location.href='updateStation/${station.stationId}'">update
                            </button>
                            <button class="btn-my btn-small btn-success"
                                    onclick="getModal('/RailWay/stationView/delete/${station.stationId}/')">delete
                            </button>
                        </td>
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