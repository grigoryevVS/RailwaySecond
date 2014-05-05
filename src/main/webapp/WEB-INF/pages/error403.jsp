<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>error403</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="home"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <h2>Sorry, access denied!</h2>

        <p class="lead">
            Caused You have not enough access rights to go
            to that page. Try something else!
        </p>
    </div>

    <hr>

    <div class="row-fluid">
        <div class="span8 offset3">
            <img class="img-polaroid" src="/RailWay/images/No-Access.jpg">
        </div>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>