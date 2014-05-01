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
    <div class="row-fluid">
        <div class="span4">
            <div class="row">
                <form:form method="post" action="${pageContext.request.contextPath}/routeView/add" commandName="route">
                <form:label path="title"><h4>Route title</h4></form:label>
                <form:input path="title"/>
            </div>
            <div class="row">
                <input class="btn btn-success" align="center" type="submit" value="Create"/>
                <a class="btn btn-info" href="${pageContext.request.contextPath}/routeView/routes">Back to the list</a>
            </div>
            </form:form>
        </div>

        <form action="${pageContext.request.contextPath}/routeView/addStation" method="POST">

            <div class="span8">
                <div class="row">
                    <div class="span4">
                        <h4>Select station</h4>
                        <select id="stationName" name="stationName" required/>
                        <c:forEach items="${stationList}" var="station">
                            <option>${station.name}</option>
                        </c:forEach>

                        </select>
                    </div>
                    <div class="span3 offset1">
                        <h4>Appear time</h4>
                        <input type="time" id="appearenceTime" name="appearenceTime" required/>
                    </div>
                </div>
                <div class="row">
                    <input class="btn btn-success" align="center" type="submit" value="Add station"/>
                </div>
            </div>
        </form>
    </div>

    <hr>


    <div class="jumbotron">
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
            <div class="row">
                <div class="span4">
                    <form:form method="post" action="${pageContext.request.contextPath}/routeView/clearDistanceList"
                               commandName="distanceList">
                        <input class="btn-small btn-success" align="center" type="submit" value="Clear distanceList"/>
                    </form:form>
                </div>
                <div class="span6 offset1">
                    <c:if test="${msg != null}">
                        <h4 style="color: red">
                                ${msg}
                        </h4>
                    </c:if>
                </div>
            </div>
        </c:if>
    </div>


    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>