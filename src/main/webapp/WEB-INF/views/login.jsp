<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Sign In</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="resources/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="resources/assets/css/style.css" rel="stylesheet">
    <link href="resources/assets/css/style-responsive.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="resources/assets/js/custom/username.js"></script>
    <script src="resources/assets/js/custom/checkPassword.js"></script>
	 <script src="resources/assets/js/custom/validateEmail.js"></script>
	
  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

	  <div id="login-page">
	  	<div class="container">
	  	
		      <form:form class="form-login" method="POST" action="index" commandName="userAccount">
		        <h2 class="form-login-heading">sign in now</h2>
		        <div class="login-wrap">
		       <form:input path="username" class="form-control" placeholder="User ID" />
					<form:errors path="username" cssStyle="color:#ff0000" />
					<br>
		            <form:input type="password" path="password" class="form-control" placeholder="Password" />
					<form:errors path="password" cssStyle="color:#ff0000" />
		            <label class="checkbox">
		                <span class="pull-right">
		                    <a data-toggle="modal" href="login.html#myModal"> Forgot Password?</a>
		
		                </span>
		            </label>
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i> SIGN IN</button>
		            <hr>
				</form:form>
		            <div class="registration">
		                Don't have an account yet?<br/>
		                <span>
		                    <a data-toggle="modal" href="login.html#myAccountModal"> Create an Account</a>
		
		                </span>
		            </div>
		
		        </div>
		        
		
		          <!-- Modal -->
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Forgot Password ?</h4>
		                      </div>
		                      <div class="modal-body">
		                          <p>Enter your e-mail address below to reset your password.</p>
		                          <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
		                          <button class="btn btn-theme" type="button">Submit</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          <!-- Modal -->
		          <form:form class="form-login"  method="POST" action="register" commandname="userAccount">
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myAccountModal" class="modal fade">
		          
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Create an Account</h4>
		                      </div>
		                      <div class="modal-body register">
		                      	  <p><input type="text" name="firstName" required placeholder="First Name" autocomplete="off" class="form-control placeholder-no-fix"></p>
		                          <p><input type="text" name="lastName" required placeholder="Last Name" autocomplete="off" class="form-control placeholder-no-fix"></p>
		                          <p><input type="text" name="username" required placeholder="Username" autocomplete="off" class="username form-control placeholder-no-fix"><h4><i style="color:green;" class="fa fa-check"></i><i style="color:red;" class="fa fa-times"></i></h4></p>
		                           <p><input id="pass" type="password" required name="password" placeholder="Password" autocomplete="off" class="form-control placeholder-no-fix"></p>
		                          <p><input id="repassword" type="password" required name="repassword" onkeyup="checkPassword(); return false;"  placeholder="Confirm Password" autocomplete="off" class="form-control placeholder-no-fix"></p>
		                          <p><span id="confirmMessage" class="confirmMessage"></span></p>
		                          <p><input type="text" id="email" required name="email" onblur="validateEmail();" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix"></p>
								  <p><span id="validEmail" class="validEmail"></span></p>
								  <p><input type="text" name="dob" placeholder="Date of Birth" autocomplete="off" class="form-control placeholder-no-fix"></p>
		                          <p><input type="radio" name="gender" id="gender" value="Male" class="radio-inline"> Male</input>
		                      	  <input type="radio" name="gender" id="gender" value="Female" class="radio-inline"> Female</input></p>
		                      	  
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
		                          <button class="create btn btn-theme" id="save" type="submit">Create</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		
		      </form:form>	  	
	  	
	  	</div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="resources/assets/js/jquery.js"></script>
    <script src="resources/assets/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="resources/assets/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch(["resources/assets/img/login-backdrop.jpg", "resources/assets/img/login-background.jpg"], {fade: 750, duration: 4000});
    </script>

  </body>
</html>
