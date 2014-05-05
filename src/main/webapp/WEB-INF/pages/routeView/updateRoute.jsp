<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>Update route</title>
</head>

<body>
<div class="container">
    <c:set var="activeMenu" value="routes"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>
    <div>
        <div class="row" align="center">
            <form:form cssClass="form-inline" method="post"
                       action="${pageContext.request.contextPath}/routeView/refresh"
                       commandName="route">
                <form:label path="title"><h4>Route title</h4></form:label>
                <form:input path="title"/>
                <form:hidden path="routeId"/>
                <input class="btn-large btn-success" align="center" type="submit" value="Update"/>
            </form:form>
        </div>
        <hr>
        <div align="center">
            <div class="form-inline">
                <form action="${pageContext.request.contextPath}/routeView/addStationUpdate" method="POST">
                    <label for="stationName" align="center"><h4>Select station</h4></label>
                    <select id="stationName" name="stationName" required>
                        <c:forEach items="${stationList}" var="station">
                            <option>${station.name}</option>
                        </c:forEach>
                    </select>
                    <label for="appearenceTime" align="center"><h4>Arrival time</h4></label>
                    <input type="time" id="appearenceTime" name="appearenceTime" required/>
                    <input class="btn-large btn-success" align="center" type="submit" value="Add station"/>
                </form>
            </div>
        </div>
    </div>
    <hr>
    <div>
        <h4 align="center">StationDistances</h4>
        <c:if test="${msgf != null}">
            <h4 class="error">
                    ${msgf}
            </h4>
        </c:if>
        <c:if test="${msgg != null}">
            <h4 class="validmsg">
                    ${msgg}
            </h4>
        </c:if>
        <c:if test="${!empty distanceList}">
            <table class="table table-bordered">
                <tr>
                    <th>Station name</th>
                    <th>Appearence time</th>
                    <th></th>
                </tr>
                <c:forEach items="${distanceList}" var="stationDistanceDto">
                    <tr>
                        <td>${stationDistanceDto.stationName}</td>
                        <td>${stationDistanceDto.appearenceTime}</td>
                        <td>
                            <button class="btn-my btn-small btn-success"
                                    onclick="getModal('/RailWay/routeView/deleteSDUpdate/${stationDistanceDto.stationName}/')">
                                delete
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <form:form cssClass="form-inline"
                       action="${pageContext.request.contextPath}/routeView/clearDistanceListUpdate"
                       commandName="distanceList">
                <div class="row-fluid">
                    <div class="span4">
                        <input class="btn-my btn-small btn-success"
                               onclick="getModal('/RailWay/routeView/clearDistanceListUpdate/'); return false;"
                               align="center" type="submit"
                               value="Clear distanceList"/>
                    </div>
                    <div class="span4">
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