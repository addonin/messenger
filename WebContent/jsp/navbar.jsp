<div class="container">
	<div class="navbar navbar-inverse" role="navigation">
	  <div class="container-fluid">
	    <div class="navbar-header">	      
	      <p class="navbar-brand" >SOCIAL NETWORK</p>
	    </div>
	    <div class="navbar-collapse collapse">
	      <ul class="nav navbar-nav">
	        <li>
	        	<form name="home" method="POST" action="controller">
	        		<input type="hidden" name="command" value="home">
        			<button id="home" type="submit" class="btn btn-default navbar-btn">Home</button>
    			</form>
    		</li>
	        <li>
	        	<form name="people" method="POST" action="controller">
	        		<input type="hidden" name="command" value="people">
        			<button id="people" type="submit" class="btn btn-default navbar-btn" >People</button>
    			</form>
    		</li>
	        <li>
	        	<form name="messages" method="POST" action="controller">
	        		<input type="hidden" name="command" value="messages">
        			<button id="messages" type="submit" class="btn btn-default navbar-btn">Messages <span id="unreadSum" class="badge"></span></button>        			
    			</form>
	        </li>	        
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	      	<li>
	        	<form name="home" method="POST" action="controller">
	        		<input type="hidden" name="command" value="ru">
        			<button type="submit" class="btn btn-default navbar-btn" disabled>RU</button>
    			</form>
	        </li>
	        <li>
	        	<form name="home" method="POST" action="controller">
	        		<input type="hidden" name="command" value="en">
        			<button type="submit" class="btn btn-default navbar-btn" disabled>EN</button>
    			</form>
	        </li>
			<li style="padding-left:20px;">
	        	<form name="home" method="POST" action="controller">
	        		<input type="hidden" name="command" value="logout">
        			<button type="submit" class="btn btn-default navbar-btn">Logout</button>
    			</form>
	        </li>
	      </ul>
	    </div><!--/.nav-collapse -->
	  </div><!--/.container-fluid -->
	</div>
</div>