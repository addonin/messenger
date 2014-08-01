<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

	<head>		

		<title>Sign up</title>

		<link rel="stylesheet" media="all" type="text/css" href="../resources/css/bootstrap.css" />
		<link rel="stylesheet" media="all" type="text/css" href="../resources/css/custom.css" />

		<script src="../js/jquery-1.9.0.js"></script>

	</head>

	<body>

		<div class="container">
			<div class="row">
	        	<div class="col-md-6 col-md-offset-3 center">
	            	<form name="registerForm" method="POST" action="../controller" accept-charset="utf-8" class="form">   <h2>Sign Up</h2>
	                    <h4>It's free and always will be.</h4>
	                    <input type="hidden" name="command" value="register">
	                    <div class="row">
	                        <div class="col-xs-6 col-md-6">
	                            <input type="text" name="firstname" value="" class="form-control input-lg" placeholder="First Name"  />                        
	                        </div>
	                        <div class="col-xs-6 col-md-6">
	                            <input type="text" name="lastname" value="" class="form-control input-lg" placeholder="Last Name"  />                        
	                        </div>
	                    </div>
	                    <input type="text" name="login" value="" class="form-control input-lg" placeholder="Login"  />
	                    <input type="text" name="email" value="" class="form-control input-lg" placeholder="Your Email"  />
	                    <input type="password" name="password" value="" class="form-control input-lg" placeholder="Password"  />
	                    <input type="password" name="confirm_password" value="" class="form-control input-lg" placeholder="Confirm Password"  />
	                    <span class="help-block">By clicking Create my account, you agree to our Terms and that you have read our Data Use Policy, including our Cookie Use.</span>
	                    <button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">Create my account</button>
	            	</form>          
				</div>
			</div>            
		</div>

	</body>

</html>