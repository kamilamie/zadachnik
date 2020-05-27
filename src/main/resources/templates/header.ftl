<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/css/util.css">
    <link type="text/css" rel="stylesheet" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body class="poppins-regular bg-light">
<nav class="navbar navbar-expand-lg sticky-top navbar-light bg-white">
    <div class="container">
        <a class="navbar-brand" href="/problems">Zadachnik</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/problems">Problems<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/assignments">Assignments</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Profile
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Info</a>
                        <a class="dropdown-item" href="/assignments">Assignments</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item drop" href="/logout">Exit <span class="fa fa-sign-out-alt"> </span></a>
                    </div>
                </li>
            </ul>
            <#if role?? && role=="TEACHER">
                <div id="newAssignButton" class="btn-group-sm" hidden>
                    <button type="button" onclick="collectCheckedProblems()" class="btn btn-outline-dark" data-toggle="modal" data-target="#newAssignModal">
                        <span class="fa fa-list-ol"></span> New assignment
                    </button>
                    <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#newProblemModal">
                        <span class="fa fa-plus"></span> New problem
                    </button>
                </div>
                <br>
            </#if>
        </div>
    </div>
</nav>

<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/popper.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>

</body>
</html>