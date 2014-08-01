<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	
	<head>
		<title>Insert title here</title>
		<link rel="stylesheet" media="all" type="text/css" href="resources/css/bootstrap.css" />
        <link rel="stylesheet" media="all" type="text/css" href="resources/css/font-awesome.css" />
		<link rel="stylesheet" media="all" type="text/css" href="resources/css/custom.css" />		
		<script src="resources/js/jquery-2.1.0.min.js"><jsp:text /></script>
		<script src="resources/js/bootstrap.min.js"><jsp:text /></script>
		<script src="resources/js/main.js"><jsp:text /></script>
	</head>
	
	<body>

		<jsp:include page="navbar.jsp"></jsp:include>
		
		<div class="container">
			<div class="row">			
				<div class="col-lg-4">	
					<div class="input-group custom-search-form">
		            	<input type="text" class="form-control" name="findusers" id="findusers">
		            	<span class="input-group-btn"></span>
		            	<button class="btn btn-default" type="button">Search</button>
			        </div>
		        </div>
		    </div>
		    <div class="row">
				<div class="col-lg-4">		            
		            <div id="findusersresult">
		            </div>
		        </div>
				<div class="col-xs-12 col-sm-6 col-md-6" id="userinfo">
		            <div class="well well-sm">
		                <div class="row">
		                    <div class="col-sm-6 col-md-4" id="userimage">
		                        <img src="" alt="" class="img-rounded img-responsive" />
		                    </div>
		                    <div class="col-sm-6 col-md-8">
		                        <h4 id="userfirstlastname"></h4>
		                        <p>
		                            <i class="glyphicon glyphicon-envelope"></i><span id="useremail"></span>
		                            <br />
		                            <i class="glyphicon glyphicon-info-sign"></i><span id="userinfo"></span>
		                            <br />
		                        </p>				                        
		                    </div>
		                </div>
		            </div>
		        </div>
			</div>
		</div>
	
	</body>
	
</html>