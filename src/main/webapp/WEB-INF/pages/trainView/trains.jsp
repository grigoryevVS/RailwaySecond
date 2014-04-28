<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

<form:form method="post" action="createTrain" commandName="train">

    <table>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add train"/>
            </td>
        </tr>
    </table>
</form:form>


<h4>Trains</h4>
<c:if test="${msg != null}">
    <h4 style="color: red">
            ${msg}
    </h4>
</c:if>
<c:if  test="${!empty trainList}">
    <table>
        <tr>
            <th>TrainId</th>
            <th>Name</th>
            <th>Capacity</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${trainList}" var="train">
            <tr>
                <td>${train.trainId}</td>
                <td>${train.name}</td>
                <td>${train.numberOfSeats}</td>
                <td><a href="updateTrain/${train.trainId}">update</a></td>
                <td><a href="delete/${train.trainId}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>