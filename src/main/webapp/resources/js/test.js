/**
 * Created by Вадим on 03.05.2015.
 */
function QuestionObject(title, id, content, answers) { // id: product-stub
    this.title = title;
    this.id = id;
    this.content = content;
    this.answers = answers;
}

QuestionObject.prototype.getStub = function () {
    var clone = $("#test").clone();
    clone.removeAttr("id");
    return clone;
};

QuestionObject.prototype.build = function () {
    var question = this.getStub();
    var ansLength = this.answers.length;
    var checkBoxDiv = question.find("#checkboxes");
    question.find(".questionTitle").text((this.id + 1)+ ". " + this.title);
    question.find(".java").append(this.content);
    question.attr("id", "test" + this.id);
    for (var i = 0; i < ansLength; i++) {
        var ans = this.answers[i];
        checkBoxDiv.append("<li class='checkbox'><label><input type=checkbox name=" + "question-" + this.id + "_answer-" + ans.id + ">" + ans.content + "</label></li>");
    }
    question.append("<button id=" + this.id + " class='confirm btn btn-success btn-sm'>Ответить</button>")
    return question;
};
