<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Stations management</title>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <form:form method="post" action="createStation" commandName="station">
            <input class="btn btn-danger" type="submit" value="Add station"/>
        </form:form>


        <h4>Stations</h4>
        <c:if test="${msg != null}">
            <h4 style="color: red">
                    ${msg}
            </h4>
        </c:if>
        <c:if test="${!empty stationList}">
            <table class="table table-bordered">
                <tr>
                    <th>StationId</th>
                    <th>Name</th>
                    <th>Update station</th>
                    <th>Delete station</th>
                </tr>
                <c:forEach items="${stationList}" var="station">
                    <tr>
                        <td>${station.stationId}</td>
                        <td>${station.name}</td>
                        <td><a class="btn-small btn-danger" href="updateStation/${station.stationId}">update</a></td>
                        <td><a class="btn-small btn-danger" href="delete/${station.stationId}">delete</a></td>
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