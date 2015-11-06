<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/" var="urlHome" />

<footer class="navbar navbar-fixed-bottom ${param.footerClass}">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlHome}">Test Portal</a>
        </div>
    </div>
</footer>

<%--Include js--%>
<script type="text/javascript" src="/resources/bower_components/jquery/dist/jquery.js"></script>
<script type="text/javascript" src="/resources/bower_components/jquery.countdown/dist/jquery.countdown.js"></script>
<script type="text/javascript" src="/resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/bower_components/bootstrap-material-design/dist/js/material.js"></script>
<script type="text/javascript" src="/resources/bower_components/bootstrap-material-design/dist/js/ripples.js"></script>
<script type="text/javascript" src="/resources/bower_components/bootstrap-paginator/src/bootstrap-paginator.js"></script>
<script type="text/javascript" src="/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/resources/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/main.js"></script>
<script type="text/javascript" src="/resources/js/verify.js"></script>
<script type="text/javascript" src="/resources/js/verify.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.json-2.4.js"></script>
<c:choose>
    <c:when test="${param.page eq 'assignTest'}">
        <script type="text/javascript" src="/resources/js/dataTable/assign-tests.js"></script>
    </c:when>
    <c:when test="${param.page eq 'addTest'}">
        <script type="text/javascript" src="/resources/js/dataTable/question-datatable.js"></script>
    </c:when>

</c:choose>
<script>
    $(document).ready(function () {
        $("#" + "${param.page}").parent().addClass("active");
    });
</script>