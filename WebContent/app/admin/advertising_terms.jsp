<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
      MAIN CONTENT2
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper site-min-height">
          	<h3><i class="fa fa-angle-right"></i> Advertising Terms</h3>
          	
			<div class="row mt">
                  <div class="col-md-12">
                  
                      
                      <div class="content-panel">
                      <span>${ successAlert }</span>

                      <div class="col-md-6" style = "margin-bottom:15px">
										<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#addTermModal">Add new package</button>
									</div>
                          <section id="unseen">
                          
                          
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>
                              <tr>
                                  <!--  <th class="numeric">Term ID</th> -->
                                  <th>Name</th>
                                  <th class="numeric">Period (Days)</th>
                                  <th>Price (Pula)</th>
                                  <th class="numeric">Action</th>
                              </tr>
                              </thead>
                              
                              <tbody>
                              
                              <!-- LOOP THIS -->
						<c:forEach items="${terms}" var="term">
                              	<tr>
                              	<!--
                              	  <td class="numeric">
                              	    <span>${ term.termId }</span><br/>                        	  
                              	  </td>
                              	  --> 
                              	  <!-- Name -->
                              	  <td>${ term.packageName}</td>
                                  <td class="numeric">${ term.period }</td>
                                  <!-- Details -->
                                  <td class="numeric">${ term.price }</td>                                
									<!-- ACTION -->            
                                  <td class="numeric">
	                                  <div class="btn-group">
										  <button type="button" class="btn btn-theme dropdown-toggle" data-toggle="dropdown">
										    Action <span class="caret"></span>
										  </button>
										  <ul class="dropdown-menu" role="menu">
										    <li><a href="../../EditTermsServlet?termId=${ term.termId }">Edit</a></li>
										    <li><a href="../../EditTermsServlet?termId=${ term.termId }">Delete</a></li>
										  </ul>
									</div>
                                  </td>
                              </tr>
                              
                              </c:forEach>
                              
                              
                              <!-- LOOP THIS -->
                              
                                </tbody>
                          </table>
	                                  		
                                                       
                           
                          </section>
                  </div><!-- /content-panel -->
                      
                      
                      
                      
                  </div><!-- /col-md-12 -->
              </div><!-- /row -->	
          	
          	
          	
          	
          		<!-- POP UP MODAL (add Term)-->
						<div class="modal fade" id="addTermModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" style="color:#fff;" aria-hidden="true">&times;</button>
						        <h4 class="modal-title" id="myModalLabel">Edit Profile</h4>
						      </div>
						      
						      <div class="modal-body">
						      	
						      
									<form id="addTerm" class="form-horizontal style-form" action="../../AdvertisingTermsServlet" method="post">
									
									
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Package Name</label>
				                        	<div class="col-sm-10">
				                        		<input type="text" name="packageName" id="packageName" class="form-control" value="" required>
				                        	</div>
		                          		</div>
		                          		<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Period (No. of Days)</label>
				                        	<div class="col-sm-10">
				                        		<input type="text" name="period" id="period" class="form-control" value="" required>
				                        	</div>
		                          		</div>
										<div class="form-group">
		                              		<label class="col-sm-2 col-sm-2 control-label">Price (BWP)</label>
				                        	<div class="col-sm-10">
				                        		<input type="number" name="price" id="price" class="form-control" value="" required>
				                        	</div>
		                          		</div>
		                          		
		                          		<div class="modal-footer" style="border-top:none;">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						        <button name="button" value="add" type="submit" class="btn btn-primary">Add</button>
						      </div>
		                          		
	                   				</form>
	                   
						      </div>
						      
						    </div>
						  </div>
						</div>      
						
						<!-- POp UP MODAL -->	
      				
								
      		
			
		</section>
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
      
      <!--Include Footer-->
      <%@ include file="include/ui_elements/footer.jsp" %>
      
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="../assets/js/jquery.js"></script>
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
			$('#addTerm').validate({
				
				rules: {
					
					packageName: {
						required: true,
					},
					
					period: {
						required: true,
					},
					
					price: {
						required: true,
					}
				},
				
				messages: {
					
					packageName: {
						required: "Please enter a name for the package.",
					},
					
					period: {
						required: "Please enter the period (No. of days listed).",
					},
					
					price: {
						required: "Please enter the package price.",
					}
					
				}	
			});
		});
	
	</script>

	
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
