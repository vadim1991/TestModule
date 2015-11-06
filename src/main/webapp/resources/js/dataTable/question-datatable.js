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
            {"data": "updateLink"},
            {"data": "deleteLink"}
        ],
        "rowCallback": function (row, data) {
            if ($.inArray(data.questionID, selected) !== -1) {
                $(row).addClass('selected');
            }
        }

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