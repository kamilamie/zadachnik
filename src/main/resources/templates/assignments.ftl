<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <meta charset="UTF-8">
    <title>My Assignments</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.8.1/dist/bootstrap-table.min.css">
    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-table@1.8.1/dist/extensions/filter-control/bootstrap-table-filter-control.min.css">

</head>

<body>
<div class="container col-sm-8">

    <div class="mt-4">
        <#if assignments??>
            <div class="shadow">
                <table class="table table-no-bordered bg-white text-center"  data-toggle="table" data-filter-control="true">
                    <thead>
                    <tr class="table-info">
                        <th scope="col">Title</th>
                        <#if role?? && role == "TEACHER">
                        <th scope="col" data-filter-control="input" data-field="student">Student</th>
                        <th scope="col" data-filter-control="select" data-field="group">Group</th>
                        <#else>
                        <th scope="col" data-filter-control="input" data-field="teacher">Teacher</th>
                        </#if>
                        <th scope="col" data-sortable="true">Creation date</th>
                        <th scope="col" data-sortable="true">Deadline</th>
                        <th scope="col" data-filter-control="select" data-field="completion">Completed</th>
                        <th scope="col" data-field="attempts">Attempts</th>
                        <th scope="col">Go to</th>
                    </tr>
                    </thead>
                    <tbody id="assignments">
                    <#list assignments as assignment>
                        <tr>
                            <td>${assignment.title}</td>
                            <#if role?? && role == "TEACHER">
                            <td>${assignment.student.name} ${assignment.student.surname}</td>
                            <td>${assignment.student.group.name}</td>
                            <#else>
                            <td>${assignment.teacher.name} ${assignment.teacher.surname}</td>
                            </#if>
                            <td>${assignment.creationDate}</td>
                            <td>${assignment.deadline}</td>
                            <td>
                                <#if assignment.completion??>
                                    yes
                                <#else>
                                    no
                                </#if>
                            </td>
                            <td>${assignment.attempts}</td>
                            <td><a href="/assignments/${assignment.id}">find more</a></td>
                        </tr>
                    </#list>

                    </tbody>
                </table>
            </div>
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

<script src="https://unpkg.com/bootstrap-table@1.8.1/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.8.1/dist/extensions/filter-control/bootstrap-table-filter-control.min.js"></script>

</body>
</html>