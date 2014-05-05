<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>error404</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="home"/>
    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <h2>We can't find this page! error 404</h2>

        <p class="lead">
            Caused by incorrect request to your browser!
            Please check your request in the browser, it's might
            not correct.
        </p>
    </div>

    <hr>

    <div class="row-fluid">
        <div class="span8 offset2">
            <img src="/RailWay/images/page.jpg">
        </div>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>