<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="complete-dialog" class="modal fade" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">Remove selected tests</h4>
        <hr>
      </div>
      <form class="form-horizontal" action="/category/create" method="post">
        <div class="modal-body">
          <div class="form-group">
            <div>Are you sure that you want remove selected tests?</div>
          </div>
        </div>
        <div class="modal-footer">
          <a data-dismiss="modal" class="btn btn-default">Cancel</a>
          <button type="submit" class="btn btn-primary">Confirm</button>
        </div>
      </form>
    </div>
  </div>
</div>
