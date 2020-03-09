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
          	<h3><i class="fa fa-angle-right"></i> Summary</h3>
          	
			<div class="row mt">
                  <div class="col-md-12">
                  
                      
                      
                      <div class="content-panel">
                      <h4><i class="fa fa-angle-right"></i> By Listing Type</h4>
                          <section id="unseen">
                          
                            	<table class="table table-hover">
	                  	  	  <hr>
	                              <thead>
	                              <tr>
	                                  <th>Listed For</th>
	                                  <th>Number of properties</th>
	                              </tr>
	                              </thead>
	                              <tbody>
	                              <tr>
	                                  <td>Rent</td>
	                                  <td>${ listedForCount["Rent"] }</td>
	                              </tr>
	                              <tr>
	                                  <td>Sale</td>
	                                  <td>${ listedForCount["Sale"] }</td>
	                              </tr>
	                              </tbody>
	                          </table>

                          </section><br/><br/>
                          
                          <h4><i class="fa fa-angle-right"></i> By Status</h4>
                          <section id="unseen">
                          
                            	<table class="table table-hover">
	                  	  	  <hr>
	                              <thead>
	                              <tr>
	                                  <th>Status</th>
	                                  <th>Number of properties</th>
	                              </tr>
	                              </thead>
	                              <tbody>
	                              <tr>
	                                  <td>Expired</td>
	                                  <td>${ statusCount["expired"] }</td>
	                              </tr>
	                              <tr>
	                                  <td>Active</td>
	                                  <td>${ statusCount["active"] }</td>
	                              </tr>
	                              <tr>
	                                  <td>Sold</td>
	                                  <td>${ statusCount["sold"] }</td>
	                              </tr>
	                              </tbody>
	                          </table>

                          </section><br/><br/>
                          
                          <h4><i class="fa fa-angle-right"></i> By Property Type</h4>
                          <section id="unseen">
                          
                            	<table class="table table-hover">
	                  	  	  <hr>
	                              <thead>
	                              <tr>
	                                  <th>Type</th>
	                                  <th>Number of properties</th>
	                              </tr>
	                              </thead>
	                              <tbody>
	                              <tr>
	                                  <td>Residential</td>
	                                  <td>${ typeCount["res"] }</td>
	                              </tr>
	                              <tr>
	                                  <td>Commercial</td>
	                                  <td>${ typeCount["com"] }</td>
	                              </tr>
	                              <tr>
	                                  <td>Industrial</td>
	                                  <td>${ typeCount["ind"] }</td>
	                              </tr>
	                              <tr>
	                              		<td>Land</td>
	                                  <td>${ typeCount["lan"] }</td>
	                              </tr>
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
