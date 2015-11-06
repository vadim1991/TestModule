function createTestDataTable() {
    var selected = [];
    $('#tests-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "pagination": true,
        "ajax": "/test/pages",
        "columns": [
            { "data": "id" },
            { "data": "testTitle" },
            { "data": "creationDate" },
            { "data": "questionAmount" },
            { "data": "timeOfTest" },
            { "data": "updateLink" }
        ],
        "rowCallback": function( row, data ) {
            if ( $.inArray(data.id, selected) !== -1 ) {
                $(row).addClass('selected');
            }
        }

    });
    $("#confirm").click(function () {
        var testIDsVal = $("#testIDs").val();
        var testIDsArray = testIDsVal.split(",");
        $.ajax({
            url: "/tests/delete",
            method: "post",
            contentType: "application/json; charset=utf-8",
            data: $.toJSON(testIDsArray),
            success: function (data) {
                if (data == "success") {
                    $("#cancel").click();
                    var table = $('#tests-table').DataTable();
                    table.row('.selected').remove().draw();
                    $("#testIDs").attr("value", "");
                    $("#count-tests").html(0);
                    selected = [];
                }
            }
        })
    });

    $('#tests-table tbody').on('click', 'tr', function () {
        var firstChild = $(this).children("td:first");
        var id = firstChild.html();
        var index = $.inArray(id, selected);

        if ( index === -1 ) {
            selected.push( id );
        } else {
            selected.splice( index, 1 );
        }
        $("#count-tests").html(selected.length);
        $("#testIDs").attr("value", selected);
        $(this).toggleClass('selected');
    } );
};

function createProfileDataTable() {
    var selected = [];
    $('#profile-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "pagination": true,
        "ajax": "/profile/pages",
        "columns": [
            { "data": "id" },
            { "data": "name" },
            { "data": "surname" },
            { "data": "email"},
            { "data": "age" }
        ],
        "rowCallback": function( row, data ) {
            if ( $.inArray(data.id, selected) !== -1 ) {
                $(row).addClass('selected');
            }
        }

    } );
    $('#profile-table tbody').on('click', 'tr', function () {
        var firstChild = $(this).children("td:first");
        var id = firstChild.html();
        var index = $.inArray(id, selected);

        if ( index === -1 ) {
            selected.push( id );
        } else {
            selected.splice( index, 1 );
        }
        $("#count-profiles").html(selected.length);
        $("#profileIDs").attr("value", selected);
        $(this).toggleClass('selected');
    } );
};
$(document).ready(function() {
    createTestDataTable();
    createProfileDataTable();
});
