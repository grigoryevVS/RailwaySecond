<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="header.jsp" %>

<h2>Add a new train</h2>

<form:form method="post" action="createTrain" commandName="train">

    <table>
        <tr>
            <td>Name </td><form:input path="name"></form:input>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add train"/>
            </td>
        </tr>
    </table>
</form:form>


<h3>Trains:</h3>
<c:if  test="${!empty trainList}">
    <table>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${trainList}" var="train">
            <tr>
                <td>${train.trainId}</td>
                <td>${train.name}</td>
                <td><a href="delete/${train.trainId}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>