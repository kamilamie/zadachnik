<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <meta charset="UTF-8">
    <title>My Assignments</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
</head>

<body>
<div class="container col-sm-8">
    <div style="margin-top: 50px">
        <#if assignments??>
            <#list assignments as assignment>
                <div class="rounded shadow bg-white">
                    <p>${assignment.title}</p>
                    <p>Student: ${assignment.student.name} ${assignment.student.surname}</p>
                    <p>Due date: ${assignment.deadline}</p>
                    <#if assignment.completion??>
                        <p>${assignment.completion}</p>
                    <#else>
                        <p>not completed</p>
                    </#if>
                    <p>${assignment.attempts} attempts</p>
                    <small><a href="/assignments/${assignment.id}">find more</a></small>
                </div>
            </#list>
        <#else>
            <div class="text-center">
                <h4>You don't have any assignments yet</h4>
                <p>
                    <a href="/problems">go to problems</a>
                </p>
            </div>
        </#if>
    </div>

</div>
</body>
</html>