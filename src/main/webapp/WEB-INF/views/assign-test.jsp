<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <jsp:include page="../views/common/head.jsp"></jsp:include>
  <title>Add Test</title>
</head>
<body>
<div class="container">
  <div class="jumbotron">
    <c:if test="${not empty msg}">
      <div class="alert alert-dismissable alert-material-cyan-300">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <p>${msg}</p>
      </div>
    </c:if>
    <form:form class="form-horizontal" action="/test/create" method="post" modelAttribute="assignTest">

      <h2>Assign Tests</h2>

      <spring:bind path="groupIDs">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <label for="testTitle" class="col-lg-2 control-label">Title</label>

          <div class="col-lg-10">
            <form:input path="groupIDs" type="text" id="groupIDs" class="form-control"
                        cssClass="form-control"
                        placeholder="Title"/>
            <span class="text-danger"><form:errors path="groupIDs" class="control-label"/></span>
          </div>
        </div>
      </spring:bind>
      <input type="hidden" name="testIDs" value="" id="testIDs">
      <div class="form-group">
        <div class="datatable-block">
          <p>Test</p>
          <p>Chooses tests : <span id="count-tests">0</span></p>
          <table id="tests-table" class="table table-striped table-hover table-bordered" cellspacing="0"
                 width="100%">
            <thead>
            <tr>
              <th>Test ID</th>
              <th>Test title</th>
              <th>Test creation date</th>
              <th>Test duration</th>
            </tr>
            </thead>
          </table>
        </div>
      </div>
      <div class="form-group">
        <div class="col-lg-10 col-lg-offset-2">
          <a href="/test/create" class="btn btn-default">Cancel</a>
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </div>
    </form:form>
  </div>
</div>
<jsp:include page="../views/common/footer.jsp">
  <jsp:param name="page" value="assignTest"></jsp:param>
</jsp:include>
</body>
</html>