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
        <br>
        <form>
            <div class="form-group">
                <label for="solutionText">Solution</label>
                <textarea class="form-control" id="solutionText" rows="5"
                          placeholder="Write your decision here or insert a link to your repository"></textarea>
            </div>
            <button type="submit" class="btn btn-info left-pill">Submit</button>
        </form>

    </div>

</div>
</body>
</html>