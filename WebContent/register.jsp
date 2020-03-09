 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>




<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    
    

    <title>BOPA | Register</title>

    <!-- Bootstrap core CSS -->
    <link href="app/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="app/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="app/assets/css/style.css" rel="stylesheet">
    <link href="app/assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    
    <style>
    
    label.error {
    	color:#ff1a1a;
    	margin-top:2px;
    	margin-bottom:17px;
    	margin-left:1px;
    }
    
    
    </style>
    
  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

	  <div id="login-page">
	  
	  	<div class="container">
	  	
		      <form id="registerForm" class="form-login" action="register" method="post">
		        <h2 class="form-login-heading">sign up</h2>
		        <div class="login-wrap">
		         
		         	<label class="error"><b>${errorMessage}</b></label> <br/>
		        
                    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name" autofocus required>
                    <br/>
                    
                    <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name" autofocus required>
		            <br/>
		            
		            <!--  =================================================  Radio Buttons ============================================================== -->
		            
                    <label class="radio-inline"><input type="radio" name="gender" id="gender" value="M" autofocus>Male</label>
                    <label class="radio-inline"><input type="radio" name="gender" id="gender" value="F" autofocus>Female</label>
		            <label class="radio-inline" style="display:none;"><input type="radio" name="gender" id="gender" value="N" autofocus checked="checked" style="display:none;">None</label>        
                                
                    <!-- <label class="radio-inline"><input type="radio" name="gender" id="otherRadio" value="O">Other</label>  -->
                                
                      <!--  =========================== ============================================================  ====================================-->
                            
		            <br/><br/>
		            
		            <input type="text" class="form-control" name="oomangNo" id="oomangNo" placeholder="Oomang Number" autofocus required>
		            <br/>
		            
		            <input type="number" pattern="[37][0-9]{7}" class="form-control" name="contactNo" id="contactNo" placeholder="Contact Number" autofocus required>
		            <br/>
		            
		            <label for="start">Date of Birth:</label>
					<input type="date" id="dob" class="form-control" name="dob" id="dob" value="YYYY-MM-DD"min="1900-01-01" max="2001-12-31" autofocus required>
		            <br/>
		            
		            <textarea rows="3" class="form-control" name="postalAddress" id="postalAddress" placeholder="Postal Address..." autofocus required></textarea>
		            <br/>
		            
		            <input type="email" class="form-control" name="emailAddress" id="emailAddress" placeholder="Email Address" autofocus required>
		            <br/>
		            
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" autofocus required>
                    <br/>
                    
		            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" autofocus required>
		            <br/>
		            
		            
		           <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> SIGN UP</button>  -
		            
		            <hr>

		            
		            <div class="registration">
		                Already have an account?<br/>
		                <a class="" href="login.jsp">
		                    Sign In
		                </a><br/><br/>
		                <a class="" href="index.html">
		                    Back Home
		                </a>
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
		
		      </form>	  	
	  	
	  	</div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="app/assets/js/jquery.js"></script> 
    
   
    
    <!-- =====================================================SCRIPT FOR FORM VALIDATION======================================================================== -->
    <script src="app/assets/js/jquery.validate.js"></script> 
    
    
    
   
	<script type="text/javascript">

		$(document).ready(function(){
			$('#registerForm').validate({
				
				rules: {
					password: {
						minlength: 8
					},
					
					confirmPassword: {
						minlength: 8,
						equalTo: '#password'
					}
				},
				
				messages: {
					
					firstName: {
						required: "Please enter your first name."
					},
			
					lastName: {
						required: "Please enter your last name."
					},
			
					gender: {
						required: "Please select your gender."
					},
					
					oomangNo: {
						required: "Please enter your ID number."
					},
					
					contactNo: {
						required: "Please enter your contact number."
					},
					
					dob: {
						required: "Please select your date of birth."
					},
					
					postalAddress: {
						required: "Please enter your postal address."
					},
					
					emailAddress: {
						required: "Please enter your email address."
					},
					
					password: {
						required: "Please enter password.",
					},
			
					confirmPassword: {
						required: "Please confirm password.",
						equalTo: 'Entered passwords do not match.'
					}		
			
				}
			
			
			});
		});
	
	</script>
	<!-- =====================================================SCRIPT FOR FORM VALIDATION======================================================================== -->
    
    <script src="app/assets/js/bootsrap.min.js"></script>
    
    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="app/assets/js/jquery.backstretch.min.js"></script>
    <script>
    	$.backstretch("img/architecture-1727807_1920.jpg", {speed: 500});
    </script>

	
	
  </body>
</html>
