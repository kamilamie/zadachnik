<#ftl encoding='UTF-8'>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/util.css">
    <link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

</head>
<body class="bg-light poppins-regular">
<div class="container">
    <div class="row justify-content-between col-sm-11 shadow bg-white rounded m-5">
        <div class="col-sm-6">
            <img src="/images/sign-up.png" class="pic-v-center">
        </div>

        <div class="col-sm-6 border-left">
            <div class="d-flex justify-content-center">

                <div class="col-sm-11 p-5 ">
                    <div class="text-center mb-4">
                        <h3>Sign Up</h3>
                    </div>

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
                            <select class="form-control" id="group" name="group" required>
                                <option>Select group...</option>
                                <#list groups as group>
                                    <option value="${group.name}">${group.name}</option>
                                </#list>
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
                    <hr>
                    <div class="text-center mt-5">
                        <small>Already have an account? <a href="/login">log in</a></small>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/popper.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
</body>
</html>