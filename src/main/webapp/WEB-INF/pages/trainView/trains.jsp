<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>List of trains</title>
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="trains" />
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <form:form method="post" action="createTrain" commandName="train">
            <input class="btn btn-success" type="submit" value="Add train"/>
        </form:form>


        <h4>Trains</h4>
        <c:if test="${msgg != null}">
            <div class="validmsg">
                    ${msgg}
            </div>
        </c:if>
        <c:if test="${msgf != null}">
            <div class="error">
                    ${msgf}
            </div>
        </c:if>
        <c:if test="${!empty trainList}">
            <table class="table table-bordered">
                <tr>
                    <th>Name</th>
                    <th>Capacity</th>
                    <th>Management</th>
                </tr>
                <c:forEach items="${trainList}" var="train">
                    <tr>
                        <td>${train.name}</td>
                        <td>${train.numberOfSeats}</td>
                        <td>
                            <button class="btn-my btn-small btn-success" onclick="location.href='updateTrain/${train.trainId}'">update</button>
                            <button class="btn-my btn-small btn-success" onclick="getModal('/RailWay/trainView/delete/${train.trainId}/')">delete</button>
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