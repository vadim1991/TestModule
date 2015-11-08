<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="complete-dialog" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Add new Group</h4>
            </div>
            <hr>
            <br>
            <form class="form-horizontal" action="/group/create" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="groupName" class="col-lg-2 control-label">Title</label>

                        <div class="col-lg-10">
                            <input name="groupName" data-validate="required" type="text" id="groupName"
                                   class="form-control"
                                   placeholder="title"/>
                            <span class="text-danger"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a data-dismiss="modal" class="btn btn-default">Cancel</a>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
