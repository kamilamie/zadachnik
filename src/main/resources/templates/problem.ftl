<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <meta charset="UTF-8">
    <title>Problem ${problem.id}</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
</head>

<body>
<div class="container col-sm-8">
    <div style="margin-top: 50px">

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

</div>
</body>
</html>