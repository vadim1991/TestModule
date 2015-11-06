function createQuestionDataTable() {
    var IDs = $("#questionIDs").val();
    var selected = [];
    if (IDs.length != 0) {
        selected = IDs.split(",");
        $("#count-questions").html(selected.length);
    }
    $('#questions-table').DataTable({
        "processing": true,
        "serverSide": true,
        "pagination": true,
        "ajax": "/questions/pages",
        "columns": [
            {"data": "questionID"},
            {"data": "title"},
            {"data": "category"},
            {"data": "updateLink"}
        ],
        "rowCallback": function (row, data) {
            if ($.inArray(data.questionID, selected) !== -1) {
                $(row).addClass('selected');
            }
        }

    });
    $("#confirm").click(function () {
        var questionIDsVal = $("#questionIDs").val();
        var questionIDsArray = questionIDsVal.split(",");
        $.ajax({
            url: "/admin/delete/questions",
            method: "post",
            contentType: "application/json; charset=utf-8",
            data: $.toJSON(questionIDsArray),
            success: function (data) {
                if (data == "success") {
                    $("#cancel").click();
                    var table = $('#questions-table').DataTable();
                    table.row('.selected').remove().draw();
                    selected = [];
                    $("#questionIDs").attr("value", selected);
                    $("#count-questions").html(0);
                }
            }
        })
    });
    $('#questions-table tbody').on('click', 'tr', function () {
        var firstChild = $(this).children("td:first");
        var id = firstChild.html();
        var index = $.inArray(id, selected);

        if (index === -1) {
            selected.push(id);
        } else {
            selected.splice(index, 1);
        }
        $("#count-questions").html(selected.length);
        $("#questionIDs").attr("value", selected);
        $(this).toggleClass('selected');
    });
};
$(document).ready(function() {
    createQuestionDataTable();
});