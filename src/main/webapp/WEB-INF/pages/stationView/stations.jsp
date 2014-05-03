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
            <input class="btn btn-success" type="submit" value="Add station"/>
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
                    <th>Name</th>
                    <th>Management</th>
                </tr>
                <c:forEach items="${stationList}" var="station">
                    <tr>
                        <td>${station.name}</td>
                        <td>
                            <a class="btn-my btn-small btn-success" href="updateStation/${station.stationId}">update</a>
                            <a class="btn-my btn-small btn-success" onclick="return isDelete()" href="delete/${station.stationId}">delete</a>
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