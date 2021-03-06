<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!-- REDIRECT TO MANAGEPROPSERVELET -->
<!DOCTYPE html>
<html lang="en">
  <head>
    
    <!--Include metadata-->
    <%@ include file="include/head/meta.jsp" %>
    
    <!--Include Stylesheets and Javascript-->
    <%@ include file="include/head/links.jsp" %>
    
    <!--Stylesheet-->
     <%@ include file="include/head/style.jsp" %>
    
    <title>BOPA | Seller Zone</title>
    
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
          	<h3><i class="fa fa-angle-right"></i> Manage Advertisements</h3>
          	<hr>
              <div class="row mt">
                  <div class="col-md-12">
                  
                  
                  
                  <div class="content-panel">
                  <div class="col-md-6" style = "margin-bottom:15px">
										<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#addPropertyModal">Add new property</button>
									</div>                      
                          <section id="unseen">
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>
                              <tr>
                                  <th class="numeric">ID</th>
                                  <th>Details</th>                          
                                  <th class="numeric">Status</th>
                                  <th class="numeric">Action</th>
                              </tr>
                              </thead>
                              <tbody>
                              
                              <img src="${ admin.profilePic }" class="img-circle" width="80">
                              
                              <c:forEach items="${ seller.items }" var="property">
                              
                              		<tr>
                              		<!-- ID -->
                              	  <td class="numeric">
                              	  <span>${ property.itemId }</span><br/>                      	 
                              	  </td>
                             		 <!-- Details -->
                              	  <td>                              	  
	                              	   <span>${ property.subcategory.subcategoryName } for ${ property.listedFor } in ${ property.location }</span>
                              	  		<br/><br/>
                              	  		<span id="price">P${ property.priceFormat }</span>
                              	  		<br/><br/>
	                              	  <p>
	                              	  	${ property.description }
	                              	  </p>
	                              	  <!-- Image -->	  
                              	  	<img src="${ property.image }" class="img-circle" width="80">
                              	  </td>
                              	 
                              <!-- STATUS -->
                                  <td class="numeric">
                                  
                                  	<c:if test="${ property.status == 'sold' }">
                                  		<span class="label label-success">Sold</span><br/><br/>
                                  	</c:if>
                                  	
                                  	<c:if test="${ property.status == 'active' }">
                                  		<span class="label label-warning">Active</span><br/><br/>
                                  	</c:if>
                                  	
                                  	<c:if test="${ property.status == 'expired' }">
                                  		<span class="label label-danger">Expired</span><br/><br/>
                                  	</c:if>
                                  	
                                  	<c:if test="${ property.status == 'blacklisted' }">
                                  		<span class="label label-default">Blacklisted</span><br/><br/>
                                  	</c:if>
                       	
	                                  <span id="expiry-date">Expires ${ property.adExpiryDate }</span>
                                  </td>
                              <!-- ACTION -->            
                                  <td class="numeric">
	                                  <div class="btn-group">
										  <button type="button" class="btn btn-theme dropdown-toggle" data-toggle="dropdown">
										    Action <span class="caret"></span>
										  </button>
										  <ul class="dropdown-menu" role="menu">
										    <li><a href="../../FlagSoldServlet?itemId=${ property.itemId }">Flag as Sold</a></li>
										    <li><a href="../../PaymentServlet?itemId=${ property.itemId }">Renew Advert</a></li>
										  </ul>
									</div>
                                  </td>
                                  
                              </tr>

                              </c:forEach>
                              
                        
                              
                             
                              
                                                                                     
                              </tbody>
                          </table>
                          </section>
                  </div><!-- /content-panel -->
                  
                  
                  
                  
                  
                      
                  </div><!-- /col-md-12 -->
              </div><!-- /row -->			
          		
          		
          		
          		        <!-- POP UP MODAL (ADD NEW Property)-->
						<div class="modal fade" id="addPropertyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" style="color:#fff;" aria-hidden="true">&times;</button>
						        <h4 class="modal-title" id="myModalLabel">Add Property</h4>		
											</div>
						      <div class="modal-body">
						      
						      <p>
						      	Please no
						      </p> 
						      
						        <form id="addProperty" class="form-horizontal style-form" action="../../AddPropertyServlet" method="post" enctype="multipart/form-data">
	                          		
	                          		<div class="form-group">
	                              		<label class="col-sm-2 col-sm-2 control-label">Property Type</label>
			                        	<div class="col-sm-10">
			                        	
			                        	<select name = "subcategoryId" id = "subcategoryId" autofocus required>
			                        	
				                        	<c:forEach items="${ subcategories }" var="subcategory">
				                        		<option class="form-control" value="${ subcategory.subcategoryId }">${ subcategory.subcategoryName }</option>
				                        	</c:forEach>
			                        		
			                        		
			                        	</select>			                      
			                        	</div>
			                        	
	                          		</div>
	                          		
	                          		<div class="form-group">
	                              		<label class="col-sm-2 col-sm-2 control-label">Listed For</label>
			                        	<div class="col-sm-10">
			                        		<select name = "listedFor" id = "listedFor" required>
				                        		<option class="form-control" value="Rent">Rent</option>
				                        		<option class="form-control" value="Sale">Sale</option>
			                        		</select>
			                        	</div>
	                          		</div>
	                          		
	                          		<div class="form-group">
	                              		<label class="col-sm-2 col-sm-2 control-label">Location</label>
			                        	<div class="col-sm-10">
			                        		<input type="text" id="location" name="location" class="form-control" name="location" id="location" required>
			                        	</div>
	                          		</div>
	                          			                          		
	                          		<div class="form-group">
	                              		<label class="col-sm-2 col-sm-2 control-label">Description</label>
			                        	<div class="col-sm-10">
			                        		<textarea rows="3" class="form-control" name="description" id="description" required></textarea>
			                        	</div>
	                          		</div>
	                          		
	                          		<div class="form-group">
	                              		<label class="col-sm-2 col-sm-2 control-label">Price (BWP)</label>
			                        	<div class="col-sm-10">
			                        		<input type="number" name="price" id="price" class="form-control" required>
			                        	</div>
	                          		</div>
	                          		
	                          		
	                          		
	                          		<div class="form-group">
	                              		<label class="col-sm-2 col-sm-2 control-label">Photo</label>
			                        	<div class="col-sm-10">
			                        		<input type="file" name="photo" id="photo" accept="image/*" class="form-control" value="" style="height:100%;" required>
			                        	</div>
	                          		</div>

									<div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						        <button name="button" value="add" type="submit" class="btn btn-primary">AddProperty</button>
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
	
	<!--  
	<script type="text/javascript">
        $(document).ready(function () {
        var unique_id = $.gritter.add({
            // (string | mandatory) the heading of the notification
            title: 'Welcome to Dashgum!',
            // (string | mandatory) the text inside the notification
            text: 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo. Free version for <a href="http://blacktie.co" target="_blank" style="color:#ffd777">BlackTie.co</a>.',
            // (string | optional) the image to display on the left
            image: '../assets/img/ui-sam.jpg',
            // (bool | optional) if you want it to fade out on its own or just sit there
            sticky: true,
            // (int | optional) the time you want it to be alive for before fading out
            time: '',
            // (string | optional) the class name you want to apply to that specific message
            class_name: 'my-sticky-class'
        });

        return false;
        });
	</script>
	-->
	
	<!-- =====================================================SCRIPT FOR FORM VALIDATION======================================================================== -->
    <script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/jquery.validate.js"></script> 
    
   
	<script type="text/javascript">

		$(document).ready(function(){
			$('#addProperty').validate({
				
				rules: {
					subcategoryId: {
						required: true,
					},
					
					listedFor: {
						required: true,
					},
					
					price: {
						required: true,
					},
					
					description: {
						required: true,
					},
					
					location: {
						required: true,
					},		
					
					photo: {
						required: true,
					},
				},
				
				messages: {
					
					subcategoryId: {
						required: "Please select property type.",
					},
			
					listedFor: {
						required: "Please select the type of listing.",
					},
			
					price: {
						required: "Please enter property price.",
					},
					description: {
						required: "Please enter a brief description of the property."
					},
					location: {
						required: "Please enter property location."
					}, 
					photo: {
						required: "Please upload a photo of the property."
					},
					
				}	
			});
		});
	
	</script>
	
		<script type="text/javascript">

		$(document).ready(function(){
			$('#editProperty').validate({
				
				rules: {
					subcategoryId: {
						required: true,
					},
					
					listedFor: {
						required: true,
					},
					
					price: {
						required: true,
					},
					
					description: {
						required: true,
					},
					
					location: {
						required: true,
					},		
					
					photo: {
						required: true,
					},
				},
				
				messages: {
					
					subcategoryId: {
						required: "Please select property type.",
					},
			
					listedFor: {
						required: "Please select the type of listing.",
					},
			
					price: {
						required: "Please specify property price.",
					},
					description: {
						required: "Please enter a brief description."
					},
					location: {
						required: "Please enter property location."
					}, 
					photo: {
						required: "Please upload a photo if the property."
					},
					
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
