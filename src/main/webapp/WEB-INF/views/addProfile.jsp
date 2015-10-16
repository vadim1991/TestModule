<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Add Question</title>
</head>
<body>
<div class="container">
    <div class="page-header"><h1>Add new Question</h1></div>
    <div class="jumbotron">
        <form class="form-horizontal" action="/create/question" method="post">
            <fieldset>
                <legend>Legend</legend>
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>

                    <div class="col-lg-10">
                        <input type="text" name="login" class="form-control" id="inputLogin" placeholder="Login">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>

                    <div class="col-lg-10">
                        <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">Password</label>

                    <div class="col-lg-10">
                        <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">

                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Checkbox
                            </label>
                        </div>
                        <br>

                        <div class="togglebutton toggle">
                            <label>
                                <input type="checkbox" checked=""> Toggle button
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputFile" class="col-lg-2 control-label">File</label>

                    <div class="col-lg-10">
                        <input type="text" name="imageTitle" readonly="" class="form-control floating-label" placeholder="Browse...">
                        <input type="file" name="file" id="inputFile" multiple="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="textArea" class="col-lg-2 control-label">Textarea</label>

                    <div class="col-lg-10">
                        <textarea class="form-control" rows="3" id="textArea"></textarea>
                        <span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Radios</label>

                    <div class="col-lg-10">
                        <div class="radio radio-primary">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
                                Option one is this
                            </label>
                        </div>
                        <div class="radio radio-primary">
                            <label>
                                <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                                Option two can be something else
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">Choose tyoe answer</label>

                    <div class="col-lg-10">
                        <select class="form-control" id="select">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                        <br>
                        <select multiple="" class="form-control">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button class="btn btn-default">Cancel</button>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp"></jsp:include>
</body>
</html>