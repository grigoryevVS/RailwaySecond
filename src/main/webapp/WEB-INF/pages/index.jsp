<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="layout/styles.jsp" %>
    <meta charset="utf-8">
    <title>SBB CFF FFS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>

<body>

<div class="container">
    <c:set var="activeMenu" value="home"/>

    <%@ include file="/WEB-INF/pages/layout/headerRole.jsp" %>

    <div class="jumbotron">
        <h2>SBB market</h2>

        <p class="lead">
            Switzerland railway company offers you to
            buy tickets to our trains that goes around
            the country without interruption for over 20 years now, welcome!
        </p>
        <a class="btn btn-large btn-success" href="${pageContext.request.contextPath}/scheduleView/scheduleFilter">Timetable</a>
    </div>

    <hr>

    <!-- Example row of columns -->
    <div class="row-fluid">
        <div class="span4">
            <h3>About company</h3>

            <p class="lead">
                On our website care about the safety of your information.
                All data, protected from tampering and leakage.
                Order railway tickets is on a secure page, so buying railway tickets online safe.
                At all stages will help to fill and easy to buy air or railway ticket,
                even if you order the e-rail ticket for the first time.
            </p>
        </div>
        <div class="span8">
            <img class="img-rounded" src="/RailWay/images/just2.jpg">

        </div>
    </div>

    <hr>

    <%@ include file="/WEB-INF/pages/layout/footer.jsp" %>

</div>

</body>
</html>
