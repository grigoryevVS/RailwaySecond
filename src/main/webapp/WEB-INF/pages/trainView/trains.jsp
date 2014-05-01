<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
</head>

<body>

<div class="container">

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <form:form method="post" action="createTrain" commandName="train">
            <input class="btn btn-danger" type="submit" value="Add train"/>
        </form:form>


        <h4>Trains</h4>
        <c:if test="${msg != null}">
            <h4 style="color: red">
                    ${msg}
            </h4>
        </c:if>
        <c:if test="${!empty trainList}">
            <table class="table table-bordered">
                <tr>
                    <th>TrainId</th>
                    <th>Name</th>
                    <th>Capacity</th>
                    <th>Update train</th>
                    <th>Delete train</th>
                </tr>
                <c:forEach items="${trainList}" var="train">
                    <tr>
                        <td>${train.trainId}</td>
                        <td>${train.name}</td>
                        <td>${train.numberOfSeats}</td>
                        <td><a class="btn-small btn-danger" href="updateTrain/${train.trainId}">update</a></td>
                        <td><a class="btn-small btn-danger" href="delete/${train.trainId}">delete</a></td>
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