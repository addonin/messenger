<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

    <head>    
        <title>Welcome ${user.getUsername()}!</title>        
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
		
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
			  <li class="active"><a href="#profile" data-toggle="tab">Profile</a></li>
			  <li><a href="#edit" data-toggle="tab">Edit</a></li>
			</ul>
			
			<!-- Tab panes -->
			<div class="tab-content">
			  
			  	<div class="tab-pane active" id="profile">
			  	
					 <div class="container" >
					    <div class="row">
					        <div class="col-xs-12 col-sm-6 col-md-6">
					            <div class="well well-sm">
					                <div class="row">
					                    <div class="col-sm-6 col-md-4">
					                        <img src="${user.getPhoto()}" alt="" class="img-rounded img-responsive" />
					                    </div>
					                    <div class="col-sm-6 col-md-8">
					                        <h4>${user.getFirstname()} ${user.getLastname()}</h4>
					                        <p>
					                            <i class="glyphicon glyphicon-envelope"></i>${user.getEmail()}
					                            <br />
					                            <i class="glyphicon glyphicon-info-sign"></i><span>${user.getInfo()}</span>
					                            <br />
					                        </p>				                        
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					</div>
				
				</div>
			  
				<div class="tab-pane" id="edit">
				
					 <div class="container" >
					    <div class="row">
					        <div class="col-xs-12 col-sm-6 col-md-6">
								<form role="form"method="POST" action="controller">
		                		  <input type="hidden" name="command" value="edit">
								  <div class="form-group">
								    <label for="firstname">Change firstname</label>
								    <input type="text" class="form-control" id="firstname" name="firstname" value="${user.getFirstname()}">
								  </div>
								  <div class="form-group">
								    <label for="lastname">Change lastname</label>
								    <input type="text" class="form-control" id="lastname" name="lastname" value="${user.getLastname()}">
								  </div>
								  <div class="form-group">
								    <label for="email">Change email address</label>
								    <input type="email" class="form-control" id="email" name="email" value="${user.getEmail()}">
								  </div>
								  <div class="form-group">
								    <label for="info">Change short info</label>
								    <textarea class="form-control" rows="3" id="info" name="info">${user.getInfo()}</textarea>
								  </div>
								  <hr>
								  <div class="form-group">
								    <label for="password">Change password</label>
								    <input type="password" class="form-control" id="password" name="password" value="${user.getPassword()}">
								  </div>
								  <hr>
								  <button type="submit" class="btn btn-default">Submit</button>
								</form>
							</div>
						</div>
					</div>
					  
				</div>
			
			</div>
		
		</div>		
		
     </body>  
        
 </html>

