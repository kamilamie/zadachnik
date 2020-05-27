<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <meta charset="UTF-8">
    <title>Problem</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
</head>

<body>
<div class="container col-sm-8">
    <div class="mt-5 mb-5">
        <#if role?? && role == "TEACHER">
            <div class="float-right">
                <button class="btn btn-black" data-toggle="modal" data-target="#editProblemModal">
                    <small class="fa fa-edit"></small>
                </button>
            </div>
        </#if>
        <#list problem.topics as topic>
            <span class="badge badge-info">${topic.name()}</span>
        </#list>

        <span class="badge badge-dark">${problem.source}</span>
        <span class="badge badge-primary">${problem.difficulty}</span>
        <hr>
        <p>
            ${problem.text}
        </p>
        <hr>
        <a href="/problems">go back to the problems</a>
    </div>

    <!--Edit Problem Modal -->
    <div class="modal fade" id="editProblemModal" tabindex="-1" role="dialog" aria-labelledby="editProblemModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form method="post" action="/problems/${problem.id}/edit">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editProblemModalLabel">Edit Problem</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="problemText">Change text of the problem </label>
                            <textarea id="problemText" name="problemText" placeholder="${problem.text}" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="progrLang">Choose programming/markup language (${problem.progrLanguage})</label>
                            <select class="form-control" id="progrLang" name="progrLang">
                                <option value="" selected>not selected</option>
                                <option value="ANY">ANY</option>
                                <option value="JAVA">JAVA</option>
                                <option value="PYTHON">PYTHON</option>
                                <option value="C">C</option>
                                <option value="JS">JS</option>
                                <option value="HTML">HTML</option>
                                <option value="CSS">CSS</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="commLang">Choose communication language (${problem.commLanguage})</label>
                            <select class="form-control" id="commLang" name="commLang">
                                <option value="" selected>not selected</option>
                                <option value="RUSSIAN">RUSSIAN</option>
                                <option value="ENGLISH">ENGLISH</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="difficulty">Choose difficulty (${problem.difficulty})</label>
                            <select class="form-control" id="difficulty" name="difficulty">
                                <option value="" selected>not selected</option>
                                <option value="EASY">EASY</option>
                                <option value="MEDIUM">MEDIUM</option>
                                <option value="HARD">HARD</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="topics">Choose topic(s) (
                                <#list problem.topics as topic>
                                    ${topic.name()}
                                </#list>)</label>
                            <select class="form-control" id="topics" name="topics">
                                <option value="" selected>not selected</option>
                                <#list topics as topic>
                                    <option value="${topic.name()}">${topic.name()}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit"class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>