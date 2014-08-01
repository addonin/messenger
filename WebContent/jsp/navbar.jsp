<div class="container">
	<div class="navbar navbar-inverse" role="navigation">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="">SOCIAL NETWORK</a>
	    </div>
	    <div class="navbar-collapse collapse">
	      <ul class="nav navbar-nav">
	        <li>
	        	<form name="home" method="POST" action="controller">
	        		<input type="hidden" name="command" value="home">
        			<button type="submit" class="btn btn-default navbar-btn">Home</button>
    			</form>
    		</li>
	        <li>
	        	<form name="people" method="POST" action="controller">
	        		<input type="hidden" name="command" value="people">
        			<button type="submit" class="btn btn-default navbar-btn">People</button>
    			</form>
    		</li>
	        <li>
	        	<form name="messages" method="POST" action="controller">
	        		<input type="hidden" name="command" value="messages">
        			<button type="submit" class="btn btn-default navbar-btn">Messages</button>
    			</form>
	        </li>	        
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	      	<li>
	        	<form name="home" method="POST" action="controller">
	        		<input type="hidden" name="command" value="ru">
        			<button type="submit" class="btn btn-default navbar-btn">RU</button>
    			</form>
	        </li>
	        <li>
	        	<form name="home" method="POST" action="controller">
	        		<input type="hidden" name="command" value="en">
        			<button type="submit" class="btn btn-default navbar-btn">EN</button>
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