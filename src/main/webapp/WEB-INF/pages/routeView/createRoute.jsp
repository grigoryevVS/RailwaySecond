<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create new route</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/routeValid.js"></script>
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div>
        <div class="row" align="center">
            <form:form cssClass="form-inline" method="post" action="${pageContext.request.contextPath}/routeView/add"
                       commandName="route">
                <form:label path="title"><h4>Route title</h4></form:label>
                <form:input path="title"/>

                <input class="btn btn-success" align="center" type="submit" value="Create"/>
                <a class="btn btn-info" href="${pageContext.request.contextPath}/routeView/routes">Back to the list</a>

            </form:form>
        </div>

        <hr>

        <div align="center">
            <div class="form-inline">

                <form action="${pageContext.request.contextPath}/routeView/addStation" method="POST">
                    <label for="stationName" align="center"><h4>Select station</h4></label>
                    <select id="stationName" name="stationName" required/>
                    <c:forEach items="${stationList}" var="station">
                        <option>${station.name}</option>
                    </c:forEach>

                    </select>


                    <label for="appearenceTime" align="center"><h4>Arrival time</h4></label>
                    <input type="time" id="appearenceTime" name="appearenceTime" required/>


                    <input class="btn btn-success" align="center" type="submit" value="Add station"/>

                </form>
            </div>
        </div>
    </div>

    <hr>


    <div>
        <h4>StationDistances</h4>
        <c:if test="${!empty distanceList}">
            <table class="table table-bordered">
                <tr>
                    <th>Station name</th>
                    <th>Appearence time</th>
                </tr>
                <c:forEach items="${distanceList}" var="stationDistanceDto">
                    <tr>
                        <td>${stationDistanceDto.stationName}</td>
                        <td>${stationDistanceDto.appearenceTime}</td>
                    </tr>
                </c:forEach>
            </table>


            <form:form cssClass="form-inline" method="post"
                       action="${pageContext.request.contextPath}/routeView/clearDistanceList"
                       commandName="distanceList">
                <div class="row-fluid">
                    <div class="span4">
                <input class="btn-small btn-success" align="center" type="submit" value="Clear distanceList"/>
                    </div>
                    <div class="span4">
                <c:if test="${msg != null}">
                    <h4 style="color: red">
                            ${msg}
                    </h4>
                </c:if>
                        </div>
                </div>
            </form:form>

        </c:if>
    </div>


    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>