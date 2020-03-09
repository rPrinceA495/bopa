<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>



<!DOCTYPE html>
<html lang="en">
  <head>
    
    <!--Include metadata-->
    <%@ include file="include/head/meta.jsp" %>
    
    <!--Include Stylesheets and Javascript-->
    <%@ include file="include/head/links.jsp" %>
    
    <!--Stylesheet-->
     <%@ include file="include/head/style.jsp" %>
    
    <title>BOPA | Admin Zone</title>
    
    
  </head>

  <body>

  <section id="container" >
      
      <!--Include Header-->
      <%@ include file="include/ui_elements/header.jsp" %>
      
      <!--Include Sidebar Nav-->
      <%@ include file="include/ui_elements/nav.jsp" %>
      
            
       <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper site-min-height">
          	<h3><i class="fa fa-angle-right"></i> Profile</h3>
          	<hr>
          	<div class="row mt">
          		<div class="col-lg-12">
          		
          		
          		
          		
          		
          		<!-- WHITE PANEL - TOP admin -->
							<div class="white-panel pn">
								<div class="white-header"> 
									<h5>ADMIN</h5>
								</div>
								
								<p><img src="${ admin.profilePic }" class="img-circle" width="80"></p>
								
								
								<div class="row">
									<div class="col-md-6" style = "margin-bottom:15px">
										<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">Edit Info</button>
									</div>
									<div class="col-md-6">
										<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#passwordModal">Change Password</button>
									</div>
								</div>
								
								<!-- 
								<span>${successMessage}</span>
								<span>${errorMessage}</span>
								-->
								
							</div>
          		
          		</div>
          		
          		<div class="col-md-12">
	                  	  <div class="content-panel">
	                  	  	
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                             <h4><i class="fa fa-angle-right"></i> User Info</h4> 
		                          </tr>
		                          </thead>
		                          <tbody>
		                          <tr>
		                              <td>Name</td>
		                              <td>${ admin.firstName }</td>
		                          </tr>
		                          <tr>

		                              <td>Surname</td>
		                              <td>${ admin.lastName }</td>
		                          </tr>
		                          <tr>
		       
		                              <td>Gender</td>
		                              <td>${ admin.gender }</td>
		                          </tr>
		                          
		                          <tr>
		                              <td>Address</td>
		                              <td>${ admin.postalAddress }</td>
		                          </tr>
                          
		                          </tbody>
		                      </table>
	                  	  </div>
	                  </div><!-- /col-md-12 -->

          		
          	</div>
          	
          	
      				
						
						
						<!-- POP UP MODAL (Edit Info)-->
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" style="color:#fff;" aria-hidden="true">&times;</button>
						        <h4 class="modal-title" id="myModalLabel">Edit Profile</h4>
						      </div>
						      <div class="modal-body">
						      
						        <form id="editProfile" class="form-horizontal style-form" action="../../EditAdminServlet" method="post" enctype="multipart/form-data">
									
									
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">First Name</label>
				                        	<div class="col-sm-10">
				                        		<input type="text" name="firstName" id="firstName" class="form-control" value="${admin.firstName }" required>
				                        	</div>
		                          		</div>
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Last Name</label>
				                        	<div class="col-sm-10">
				                        		<input type="text" name="lastName" id="lastName" class="form-control" value="${admin.lastName }" required>
				                        	</div>
		                          		</div>
		                          		
										<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Gender</label>
		                              		
		                              		
		                              		
		                              		<c:if test="${admin.gender == 'M'.charAt(0)}">
		                              			<label style="margin-left:20px;">
		                              				Male
												    <input type="radio" name="gender" id="gender" value="M" checked>												  
										  		</label>
										  	<label style="margin-left:25px;">
										  			Female
												    <input type="radio" name="gender" id="gender" value="F">												    
										  	</label>
		                              		</c:if>
		                              		
		                              		<c:if test="${admin.gender == 'F'.charAt(0)}">
		                              			<label style="margin-left:20px;">
		                              				Male
												    <input type="radio" name="gender" id="gender" value="M">												    
										  		</label>
										  		<label style="margin-left:25px;">
											    	Female
											    	<input type="radio" name="gender" id="gender" value="F" checked>
										  		</label>
		                              		</c:if>
		                              		
				                        			                        	
		                          		</div>
		                          		
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Oomang Number</label>
				                        	<div class="col-sm-10">
				                        		<input type="text" name="oomangNo" id="oomangNo" class="form-control" value="${admin.oomangNo }" required>
				                        	</div>
		                          		</div>
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Date of Birth</label>
				                        	<div class="col-sm-10">
				                        		<input type="date" name="dob" id="dob" class="form-control" value="${admin.dob }" required>
				                        	</div>
		                          		</div>
		                          		
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Postal Address</label>
				                        	<div class="col-sm-10">
				                        		<textarea rows="3" class="form-control" name="postalAddress" id="postalAddress"  autofocus required>${admin.postalAddress }</textarea>
				                        	</div>
		                          		</div>
		                          		
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Email Address</label>
				                        	<div class="col-sm-10">
				                        		<input type="email" name="emailAddress" id="disabledInput" class="form-control" value="${admin.emailAddress }" disabled>				                        		
				                        	</div>
		                          		</div>
		                          		
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Profile Picture</label>
				                        	<div class="col-sm-10">
				                        		<input type="file" style="height:100%;" name="profilePic" id="profilePic" accept="image/*" class="form-control" value="">
				                        	</div>
		                          		</div>
		                          		
		                          		<div class="modal-footer" style="border-top:none;">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						        <button type="submit" name="saveChanges" class="btn btn-primary">Save changes</button>
						      </div>
		                          		
	                   				</form>
						        
						      </div>
						    
						    </div>
						  </div>
						</div>      
						
						<!-- POp UP MODAL -->				
      		
						
						<!-- POP UP MODAL (Change Password)-->
						<div class="modal fade" id="passwordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" style="color:#fff;" aria-hidden="true">&times;</button>
						        <h4 class="modal-title" id="myModalLabel">Edit Profile</h4>
						      </div>
						      
						      <div class="modal-body">
						      	
						      
									<form id="editPassword" class="form-horizontal style-form" action="../../ChangeAdminPassword" method="post">
									
									
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Current Password</label>
				                        	<div class="col-sm-10">
				                        		<input type="password" name="currentPassword" id="currentPassword" class="form-control" value="" required>
				                        	</div>
		                          		</div>
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">New Password</label>
				                        	<div class="col-sm-10">
				                        		<input type="password" name="newPassword" id="newPassword" class="form-control" value="" required>
				                        	</div>
		                          		</div>
										<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Confirm New Password</label>
				                        	<div class="col-sm-10">
				                        		<input type="password" name="confirmPassword" id="confirmPassword" class="form-control" value="" required>
				                        	</div>
		                          		</div>
		                          		
		                          		<div class="modal-footer" style="border-top:none;">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						        <button type="submit" class="btn btn-primary">Save changes</button>
						      </div>
		                          		
	                   				</form>
	                   
						      </div>
						      
						    </div>
						  </div>
						</div>      
						
						<!-- POp UP MODAL -->	
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						    
						
						<!-- POp UP MODAL -->	
			
			
		</section>
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
      
      <!--Include Footer-->
      <%@ include file="include/ui_elements/footer.jsp" %>
      
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="../assets/js/jquery-1.8.3.min.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="../assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="../assets/js/jquery.scrollTo.min.js"></script>
    <script src="../assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="../assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="../assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="../assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="../assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="../assets/js/sparkline-chart.js"></script>    
	<script src="../assets/js/zabuto_calendar.js"></script>	
	
    
    <!-- =====================================================SCRIPT FOR FORM VALIDATION======================================================================== -->
    <script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/jquery.validate.js"></script> 
    
   
	<script type="text/javascript">

		$(document).ready(function(){
			$('#editPassword').validate({
				
				rules: {
					currentPassword: {
						required: true,
						minlength: 8,
					},
					
					newPassword: {
						required: true,
						minlength: 8,
					},
					
					confirmPassword: {
						required: true,
						minlength: 8,
						equalTo: '#newPassword',
					}
				},
				
				messages: {
					
					currentPassword: {
						required: "Please enter your current password.",
					},
			
					newPassword: {
						required: "Please enter new password.",
					},
			
					confirmPassword: {
						required: "Please confirm new password.",
						equalTo: 'Entered passwords do not match.',
					}	
					
				}	
			});
		});
	
	</script>
	
	<script type="text/javascript">

		$(document).ready(function(){
			$('#editProfile').validate({
				
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
					
					dob: {
						required: "Please select your date of birth."
					},
					
					postalAddress: {
						required: "Please enter your postal address."
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
	
	
	<script type="application/javascript">
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    </script>
  

  </body>
</html>
