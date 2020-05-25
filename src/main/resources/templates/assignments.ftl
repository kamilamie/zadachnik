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
            <div class="text-center">
                <h4>You don't have any assignments yet</h4>
                <p>
                    <a href="/problems">go to problems</a>
                </p>
            </div>
        <#else>
            <#list assignments as assignment>
                <a href="/assignments/${assignment.id}">
                    <div class="rounded shadow bg-white">
                        <p>${assignment.title}</p>
                        <small>Created by ${assignment.teacher.name} ${assignment.teacher.surname}
                            at ${assignment.creationDate}</small>
                        <p>Due date: ${assignment.deadline}</p>
                    </div>
                </a>
            </#list>
        </#if>
    </div>

</div>
</body>
</html>