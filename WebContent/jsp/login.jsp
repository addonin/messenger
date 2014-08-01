<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>

		<title>Login</title>

		<link rel="stylesheet" media="all" type="text/css" href="resources/css/bootstrap.css" />
		<link rel="stylesheet" media="all" type="text/css" href="resources/css/custom.css" />

		<script src="resources/js/jquery-2.1.0.min.js"></script>

    </head>

    <body>

        <div class="container">
		    <div class="row">
		        <div class="col-sm-6 col-md-4 col-md-offset-4 center">
		            <h1 class="text-center login-title">Sign in to enter social network!</h1>
		            <div class="account-wall">		                
		                <form class="form-signin" name="loginForm" method="POST" action="controller">
		                <input type="hidden" name="command" value="login">
		                <input type="text" class="form-control" placeholder="Login" name="login" required autofocus>
		                <input type="password" class="form-control" placeholder="Password" name="password" required>
		                <button class="btn btn-lg btn-primary btn-block" type="submit">
		                    Sign in</button>
		                <label class="checkbox pull-left">
		                    <input type="checkbox" value="remember-me">
		                    Remember me
		                </label>
		                <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
		                </form>
		            </div>
		            <a href="jsp/register.jsp" class="text-center new-account">Create an account </a>
		        </div>
		    </div>
		</div>

    </body>

</html>
