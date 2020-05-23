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

    <div class="shadow">
        <table class="table table-no-bordered bg-white"  data-toggle="table" data-filter-control="true">
            <thead>
            <tr class="table-info">
                <th scope="col" data-sortable="true">#</th>
                <th scope="col" data-filter-control="input" data-field="text">Text</th>
                <th scope="col" data-filter-control="select" data-field="diffic">Difficulty</th>
                <th scope="col" data-filter-control="select" data-field="progr">Progr_lang</th>
                <th scope="col" data-filter-control="select" data-field="comm">Comm_lang</th>
                <th scope="col" data-filter-control="select" data-field="source">Source</th>
                <th scope="col" data-filter-control="select" data-field="topics">Topic</th>
            </tr>
            </thead>
            <tbody>

            <#list problems as problem>
                <tr>
                    <td>${problem.id}</td>
                    <td>${problem.text[0..*36]}...</td>
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
</body>
</html>