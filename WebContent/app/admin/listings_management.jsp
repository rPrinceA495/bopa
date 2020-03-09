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
          	<h3><i class="fa fa-angle-right"></i> Listings Management</h3>
          	
			<div class="row mt">
                  <div class="col-md-12">
                  
                      
                      
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i> Recently Extended Properties</h4>
                          <section id="unseen">
                          
                            <table class="table table-bordered table-striped table-condensed">
                              <thead>
                              <tr>
                                  <th class="numeric">User Details</th>
                                  <th>Property Details</th>                                              
                                  <th class="numeric">Expiry Date</th>
                                  <th class="numeric">Action</th>
                              </tr>
                              </thead>
                              
                              <tbody>
                              
                              <c:forEach items="${ extendedProperties }" var="property">
	                              	<tr>
	                              <!-- User ID -->
	                              	  <td>
	                              	  	 	<span>ID: ${ property.seller.sellerId }</span><br/><br/>
	                              	  	 	<span>${ property.seller.firstName } ${ property.seller.lastName }</span><br/><br/>
	                              	  </td>
	                              <!-- Details -->
	                              	  <td>
		                              	   <span>ID: ${ property.itemId }</span><br/><br/>
		                              	   <span>${ property.subcategory.subcategoryName } for ${ property.listedFor } in ${ property.location }</span><br/><br/>	                  
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
											    <li><a href="../../ListPropServlet?itemId=${ property.itemId }">List Property</a></li>
											    <li><a href="../../BlacklistServlet?itemId=${ property.itemId }">Blacklist</a></li>
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
