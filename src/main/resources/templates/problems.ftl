<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <meta charset="UTF-8">
    <title>Problems</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.8.1/dist/bootstrap-table.min.css">
    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-table@1.8.1/dist/extensions/filter-control/bootstrap-table-filter-control.min.css">
</head>

<body>
<div class="container col-sm-8">

    <div>
        <br>
    </div>

    <!--New Assignment Modal -->
    <div class="modal fade" id="newAssignModal" tabindex="-1" role="dialog" aria-labelledby="newAssignModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form method="post" action="/addAssignment">
                    <div class="modal-header">
                        <h5 class="modal-title" id="newAssignModalLabel">New Assignment</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>You chose <span id="problemsAmount"></span> problem(s)</label>
                            <input hidden id="problemsIds" name="problemsIds" value="">
                        </div>
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input id="title" name="title" class="form-control" placeholder="Topic or lesson name">
                        </div>
                        <div class="form-group">
                            <label for="group">Chose group</label>
                            <select class="form-control" id="group" name="group">
                                <option value="" selected>not selected</option>
                                <#list groups as group>
                                    <option value="${group.id}">${group.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="group">Chose a student</label>
                            <select class="form-control" id="student" name="student" data-live-search="true">
                                <option value="" selected>not selected</option>
                                <#list students as student>
                                    <option value="${student.id}">${student.name} ${student.surname}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="deadline">Choose deadline</label>
                            <input id="deadline" type="date" name="deadline" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" id="saveAssignButton" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!--New Problem Modal -->
    <div class="modal fade" id="newProblemModal" tabindex="-1" role="dialog" aria-labelledby="newProblemModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form method="post" action="/addProblem">
                    <div class="modal-header">
                        <h5 class="modal-title" id="newProblemModalLabel">New Problem</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="problemText">Add text of the problem </label>
                            <textarea id="problemText" name="problemText" required class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="progrLang">Choose programming/markup language</label>
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
                            <label for="commLang">Choose communication language</label>
                            <select class="form-control" id="commLang" name="commLang">
                                <option value="" selected>not selected</option>
                                <option value="RUSSIAN">RUSSIAN</option>
                                <option value="ENGLISH">ENGLISH</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="difficulty">Choose difficulty</label>
                            <select class="form-control" id="difficulty" name="difficulty">
                                <option value="" selected>not selected</option>
                                <option value="EASY">EASY</option>
                                <option value="MEDIUM">MEDIUM</option>
                                <option value="HARD">HARD</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="topics">Choose topic(s)</label>
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

    <div class="shadow">
        <table class="table table-no-bordered bg-white text-center"  data-toggle="table" data-filter-control="true">
            <thead>
            <tr class="table-info">
                <#if role?? && role=="TEACHER">
                    <th scope="col">Choose</th>
                </#if>
                <th scope="col" data-sortable="true">#</th>
                <th scope="col" data-filter-control="input" data-field="text">Text</th>
                <th scope="col" data-filter-control="select" data-field="diffic">Difficulty</th>
                <th scope="col" data-filter-control="select" data-field="progr">Progr_lang</th>
                <th scope="col" data-filter-control="select" data-field="comm">Comm_lang</th>
                <th scope="col" data-filter-control="select" data-field="source">Source</th>
                <th scope="col" data-filter-control="select" data-field="topics">Topic</th>
            </tr>
            </thead>
            <tbody id="problems">
                <#list problems as problem>
                    <tr>
                        <#if role?? && role=="TEACHER">
                            <td>
                                <input type="checkbox" name="${problem.id}">
                            </td>
                        </#if>
                        <td>${problem.id}</td>
                        <td>
                            <a style="color: black" target="_blank" href="/problems/${problem.id}">${problem.text[0..*36]}...</a></td>
                        <td>${problem.difficulty}</td>
                        <td>${problem.progrLanguage}</td>
                        <td>${problem.commLanguage}</td>
                        <td>${problem.source}</td>
                        <td>${problem.topics[0].name()}</td>
                    </tr>
                </#list>

            </tbody>
        </table>
    </div>

</div>
<script src="https://unpkg.com/bootstrap-table@1.8.1/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.8.1/dist/extensions/filter-control/bootstrap-table-filter-control.min.js"></script>
<script>
    $(document).ready(function() {
        document.getElementById('newAssignButton').hidden = false;
    });

    function collectCheckedProblems() {
        var selected = [];
        $('#problems input:checked').each(function() {
            selected.push($(this).attr('name'));
        });
        document.getElementById("problemsAmount").innerText = selected.length;
        if (selected.length == 0){
            document.getElementById('saveAssignButton').disabled = true;
        } else {
            document.getElementById('saveAssignButton').disabled = false;
        }
        var ids = selected.join(',');
        document.getElementById("problemsIds").setAttribute('value', ids);
    }
</script>
</body>
</html>