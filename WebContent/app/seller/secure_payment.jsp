<!-- Imports of standard meta scriplet and jstl-->
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
    
    <!--Stylesheet for chat ui elements-->
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
          	<h3><i class="fa fa-angle-right"></i> Payment</h3>
          	<hr>
          	<div class="row mt">
          		<div class="col-lg-12">
          		
          		<div class="form-panel">
					 
<form class="form-horizontal style-form" action = "../../PaymentServlet" method="post">
					 
					 	<div class="form-group">
	                              		<label class="col-sm-2 col-sm-2 control-label">Advertising Package</label>
			                        	<div class="col-sm-6">
			                        	
			                        	<select name = "termId" id = "termId" autofocus required>
			                        	
				                        	<c:forEach items="${ terms }" var="term">
				                        		<option class="form-control" value="${ term.termId }">${ term.packageDetails } for ${ term.period } days</option>
				                        	</c:forEach>
			                        		
			                        		
			                        	</select>			                      
			                        	</div>
			                        	
	                      	</div>
	                      	

                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Card Number</label>
                              <div class="col-sm-6">
                                  <input type="text" name="cardNumber" id="cardNumber" class="form-control" required>
                              </div>
                          </div>
                          
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Expiration Date (MM/YY)</label>
                              <div class="col-sm-6">
                                  <input type="text" name="expirationDate" id="expirationDate" class="form-control" required>
                              </div>
                          </div>
                          
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Check</label>
                              <div class="col-sm-2">
                                  <input type="text" class="form-control" name="check" id="check" required>
                              </div>
                          </div>
     
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Name on Card</label>
                              <div class="col-sm-6">
                                  <input type="text" class="form-control" name = "nameOnCard" id = "nameOnCard" required>
                              </div>
                          </div>
                          
                          <div class="form-group">
                              <div class="col-sm-6">
                                  <input type="hidden" class="form-control" name = "itemIdToExtend" id = "itemIdToExtend" value="${ itemIdToExtend }" required>
                              </div>
                          </div>
                          
                          <button type="submit" class="btn btn-primary">Make payment</button>
                          
                     </form>


					 
					 
				</div>
          		
					          		</div>
          	</div>
			
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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  
  	<script src="../assets/js/chat.js"></script>

  </body>
</html>
