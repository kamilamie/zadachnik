<#ftl encoding='UTF-8'>
<!DOCTYPE>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/css/util.css">
    <link type="text/css" rel="stylesheet" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

</head>
<body class="bg-light poppins-regular">
<div class="d-flex justify-content-center">
    <div class="col-sm-4 p-5 m-5 shadow bg-white rounded">
        <div class="text-center mb-5">
            <h3>Welcome</h3>
        </div>
        <#if error??>
            <p class="alert alert-primary small">Login or password are not valid!</p>
        </#if>
        <form method="post" action="/login">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" id="login" name="login" placeholder="enter login">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="enter password">
                    <div class="input-group-append">
                        <span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
                    </div>
                </div>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
                <label class="form-check-label" for="remember-me">Remember me</label>
            </div>
            <br>
            <br>
            <div class="text-center">
                <button type="submit" class="btn btn-dark col-sm-6 mt-4">LOG IN</button>
            </div>
        </form>

        <div class="text-center mt-5">
            <small>Don’t have an account? <a href="/signUp">Sign Up</a></small>
        </div>
    </div>
</div>

<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/popper.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
</body>
</html>