<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <meta charset="UTF-8">
    <title>Assignment</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
</head>

<body>
<div class="container col-sm-8">
    <div class="mt-5 mb-5">
        <div class="bg-white rounded shadow p-3 mb-5">
            <div class="float-right">
                <small class="alert-danger">Deadline: ${assignment.deadline}</small><br>
                <small>Completed:
                    <#if assignment.completion??>
                        ${assignment.completion}
                    <#else>
                        Not completed
                    </#if>
                </small><br>
                <small>Number of attempts: ${assignment.attempts}</small>
            </div>
            <h4>${assignment.title}</h4>
            <p>Created by ${assignment.teacher.name} ${assignment.teacher.surname}
                at ${assignment.creationDate}</p>
        </div>

        <#list assignment.problems as problem>
            <div class="mb-5">
                <#list problem.topics as topic>
                    <span class="badge badge-info">${topic.name()}</span>
                </#list>
                <span class="badge badge-dark">${problem.source}</span>
                <span class="badge badge-primary">${problem.difficulty}</span>
                <hr>
                <p>
                    ${problem.text}
                </p>
            </div>
        </#list>

        <#if role == "STUDENT">
            <form method="post" action="/assignments/${assignment.id}/addSolution">
                <div class="form-group">
                    <label for="solution">Add solution</label>
                    <textarea class="form-control" id="solution" rows="5"
                              name="solution"
                              placeholder="Write your decision here or insert a link to your repository. Attention: teacher sees your last submitted solution only!"></textarea>
                </div>
                <button type="submit" class="btn btn-info left-pill">Submit</button>
            </form>
        </#if>
        <#if assignment.solution??>
            <div class="mt-5">
                <label for="lastSolution">Last solution</label>
                <div class="rounded bg-white p-3">
                    ${assignment.solution}
                </div>
            </div>
        </#if>



    </div>

</div>
</body>
</html>