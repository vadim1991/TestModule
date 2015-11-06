<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="${param.id}" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Remove selected ${param.title}</h4>
                <hr>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <div>Are you sure that you want remove selected units?</div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="cancel-${param.button}" data-dismiss="modal" class="btn btn-default">Cancel</a>
                <button id="${param.button}" type="submit" class="btn btn-primary">Confirm</button>
            </div>

        </div>
    </div>
</div>
