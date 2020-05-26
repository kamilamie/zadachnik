<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Console</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/css/util.css">
</head>

<body class="bg-light">
<div class="container col-sm-5">
    <div class="justify-content-center m-5 p-3 shadow rounded bg-white">
        <div class="text-center m-4">
            <h3>Add teacher</h3>
        </div>
        <div style="margin-top: 50px">
            <form method="post" action="/signUp" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control input-item" id="login" name="login"
                           placeholder="Login">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control input-item" id="password" name="password"
                           placeholder="Password">
                </div>
                <div class="form-group">
                    <select class="form-control" id="role" name="role">
                        <option selected>Select role...</option>
                        <option value="ADMIN">admin</option>
                        <option value="TEACHER">teacher</option>
                    </select>
                </div>
                <div class="text-center" style="position: relative">
                    <button type="submit" class="btn btn-dark col-sm-6 mt-4">SIGN UP</button>
                    <#if errors??>
                        <span class="fa fa-exclamation ml-2" data-toggle="tooltip" data-placement="right"
                              title="
                            <#list errors as error>
                                ${error}
                            </#list>"
                              style="color: red;  position: absolute; top: 60%;">
                            </span>
                    </#if>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/popper.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
</body>
</html>