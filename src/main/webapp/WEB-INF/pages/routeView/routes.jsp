<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="../header.jsp" %>

<h2>Add a new route</h2>

<form:form method="post" action="create" commandName="route">

    <table>
        <tr>
            <td>Name </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add route"/>
            </td>
        </tr>
    </table>
</form:form>

