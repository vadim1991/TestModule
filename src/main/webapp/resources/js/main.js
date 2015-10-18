var testModule = function(time){
    var answers = [],
        testDuration = time||0, // duration in seconds
        $form = $('#passedTestModel'),
        $finishBtn = $('#end-test'),
        $answerInput =  $('.answer'),
        $questions = $('.question'),
        $pagination = $('#test-list'),
        $timer = $('#timer'),
        $answerBtn = $('.submit-btn'),
        questionsLength = $questions.length;


    // Init pagination
    function initPagination(){
        $questions.first().show();
        var options = {
            currentPage: 1,
            totalPages: questionsLength,
            bootstrapMajorVersion: 3,
            onPageClicked: function(e,originalEvent,type,page){
                $questions.hide();
                $questions.filter('[data-question=' + page + ']').show();
            }
        };
        $pagination.bootstrapPaginator(options);
    }

    // Set timer
    function initTimer(time){
        var testTime = new Date();
        testTime.setSeconds(testTime.getSeconds() + testDuration);

        $timer.countdown(testTime)
            .on('update.countdown', function(event) {
                var format = '%M:%S';
                $(this).html(event.strftime(format));
            })
            .on('finish.countdown', function() {
                finishTest();
            });
    }

    // Read answers from Local Storage
    function initAnswers (){
        if(localStorage.check){
            answers = JSON.parse(localStorage.check);
            answers.forEach(function(ch){
                $answerInput.filter("[data-id=" + ch + "]").attr('checked', true);
            });
        } else {
            localStorage.check = [];
        }
    }

    // Write to Local Storage
    function setAnswer (el){
        answers.push($(el).data('id'));
        localStorage.check = JSON.stringify(answers);
    }

    // Complete test
    function finishTest(){
        $form.submit();
        localStorage.clear();
    }

    return {
        init: function(){
            initPagination();
            initAnswers();
            initTimer();

            $finishBtn.on('click', function (e) {
                e.preventDefault();
                finishTest();
            });

            $answerInput.on('click', function(){
                setAnswer(this);
            });

            $answerBtn.on('click', function(){

            });
        }
    }
};

var createTestModule = function() {
    var $addBtn = $("#add"),
        $answerDiv = $("#answersDiv"),
        answerLabel = ".control-label",
        answers = ".answers";


    function addAnswer() {
        var answerIndex = $answerDiv.find(".form-group").length + 1;
        var answer = $answerDiv.find(".form-group:last-child").clone();

        answer.find(answerLabel).attr("id", "answerLabel" + answerIndex).text("Answer " + answerIndex);
        answer.find(answers).attr("id", "answerBlock" + answerIndex);
        $answerDiv.append(answer);
    }

    function deleteAnswer() {
        var answers = $("#answersDiv").find(".form-group");
        var sizeAnswers = answers.length;
        if (sizeAnswers > 2) {
            answers.last().remove();
        }
    }

    function changeInput() {
        var control = $answerDiv.find(".form-group");
        control.find(".checkbox").toggle();
        control.find(".radio").toggle();
    }

    return {
        init: function () {
            $addBtn.on('click', function () {
                addAnswer();
            });
            $("#remove-last").on('click', function () {
                deleteAnswer();
            });
            $(".type").on('change', function () {
                changeInput();
            });
        }
    };
};

(function(){
    // Init material design
    $.material.init();

    // Init add question module
    if ( $('#question').length){
        createTestModule().init();
    }
    // Init test module
    if ($('#resultTestModel').length){
        testModule().init();
    }
    $(document).on('timer', function(e, time){
        testModule(time).init();
    });

    $(document).ready(function() {

        var selected = [];

        $('#questions-table').DataTable( {
            "processing": true,
            "serverSide": true,
            "pagination": true,
            "ajax": "/questions/pages",
            "columns": [
                { "data": "questionID" },
                { "data": "title" },
                { "data": "category" },
                { "data": "updateLink" },
                { "data": "deleteLink" }
            ],
            "rowCallback": function( row, data ) {
                if ( $.inArray(data.questionID, selected) !== -1 ) {
                    $(row).addClass('selected');
                }
            }

        } );
        $('#questions-table tbody').on('click', 'tr', function () {
            var firstChild = $(this).children("td:first");
            var id = firstChild.html();
            var index = $.inArray(id, selected);

            if ( index === -1 ) {
                selected.push( id );
            } else {
                selected.splice( index, 1 );
            }
            $("#count-questions").html(selected.length);
            $("#questionIDs").attr("value", selected);
            $(this).toggleClass('selected');
        } );
    } );
    $(document).ready(function() {

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
                { "data": "timeOfTest" }
            ],
            "rowCallback": function( row, data ) {
                if ( $.inArray(data.id, selected) !== -1 ) {
                    $(row).addClass('selected');
                }
            }

        } );
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
    } );
})();

