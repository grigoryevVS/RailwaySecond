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
            <input class="btn btn-success" type="submit" value="Add train"/>
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
                    <th>Name</th>
                    <th>Capacity</th>
                    <th>Management</th>
                </tr>
                <c:forEach items="${trainList}" var="train">
                    <tr>
                        <td>${train.name}</td>
                        <td>${train.numberOfSeats}</td>
                        <td>
                            <a class="btn-my btn-small btn-success" href="updateTrain/${train.trainId}">update</a>
                            <a class="btn-my btn-small btn-success" onclick="return isDelete()" href="delete/${train.trainId}">delete</a>
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