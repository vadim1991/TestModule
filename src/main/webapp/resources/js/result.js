function ResultObject(title, id, content, answers, errors) { // id: product-stub
    this.title = title;
    this.id = id;
    this.content = content;
    this.answers = answers;
    this.errors = errors;
}

ResultObject.prototype.getStub = function () {
    var clone = $("#test").clone();
    clone.removeAttr("id");
    return clone;
};

ResultObject.prototype.build = function () {
    var question = this.getStub();
    var ansLenght = this.answers.length;
    var checkBoxDiv = question.find(".checkbox-inline");
    question.find(".java").append(this.content);
    question.attr("id", "test" + this.id);
    for (var i = 0; i < ansLenght; i++) {
        var ans = this.answers[i];
        if (ans.flag == 1) {
            checkBoxDiv.append("<li class=checkbox><input type=checkbox name=" + "question-" + this.id + "_answer-" + ans.id + " disabled>" + ans.content + "<span class='glyphicon glyphicon-ok green'></span></li>");
        } else {
            checkBoxDiv.append("<li class=checkbox><input type=checkbox name=" + "question-" + this.id + "_answer-" + ans.id + " disabled>" + ans.content + "</li>");
        }
    }
    var questionTitle = question.find(".questionTitle");
    if (this.errors[this.id] == true) {
        questionTitle.append("<span class='glyphicon glyphicon-ok green'></span>");
    } else {
        questionTitle.append("<span class='glyphicon glyphicon-remove red'></span>")
    }
    questionTitle.append((this.id + 1) + ". " + this.title);
    return question;
};
