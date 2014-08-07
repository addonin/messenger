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
                  
                  	<div class="col-md-4">
					<ul class="list-group" id="friends">
                      	<c:forEach items="${friends}" var="friend">
	                       	<li id="friend${friend.getUserID()}" class="list-group-item">${friend.getLastname()} ${friend.getFirstname()}	                       		
	                       		<a href="javascript: openChat(${user.getUserID()}, ${friend.getUserID()})">
	                       			<span id="badge${friend.getUserID()}" class="badge alert-danger friendBadge"></span>
	                       			<span class="send-message">Message</span>	                       			
	                       		</a>
	                       	</li>
		<%-- <li class="list-group-item">${friend.getLastname()} ${friend.getFirstname()}<a href="#" style="display: inline-block;"><span class="glyphicon glyphicon-envelope send-message" data-sender="${user.getUserID()}" data-receiver="${friend.getUserID()}"></span></a></li> --%>
                      	</c:forEach>
                    </ul>
				</div>
                  
				<div class="col-md-4" id="chatWindow">
				    <div class="panel panel-primary">
				        <div class="panel-heading">
				            <span class="glyphicon glyphicon-comment"></span>
				            <span id="from-to">
				        </div>
				        <div class="panel-body" id="messageArea">
				            <ul class="chat">
				            </ul>
				        </div>
				        <div class="panel-footer">
				            <div class="input-group">
				                <input id="btn-input" type="text" class="form-control input-sm" placeholder="Type your message here..." />
				                <span class="input-group-btn">
				                    <button class="btn btn-warning btn-sm" id="btn-chat">
				                        Send</button>
				                </span>
				            </div>
				        </div>
				    </div>
				</div>
                  
				<div class="col-md-4" id="unreadMessages">
				    <c:forEach items="${messages}" var="message">
				        <div class="alert alert-success">
				            <a href="#" class="close" data-dismiss="alert" data-message_id="${message.getMessageId()}">&times;</a>
				            <strong>From ${message.getSenderId()} </strong>${message.getTimestamp()}<br/>${message.getText()}
				        </div>
				<!-- <li class="list-group-item">${friend.getLastname()} ${friend.getFirstname()}<a href="#" style="display: inline-block;"><span class="glyphicon glyphicon-envelope send-message" data-sender="${user.getUserId()}" data-receiver="${friend.getUserId()}"></span></a></li>-->
				    </c:forEach>
				</div>
                  
              	</div>
                  
		</div>
	
	</body>

</html>