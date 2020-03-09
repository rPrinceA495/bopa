package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BEANS.AdvertisingTerm;
import model.DAO.AdvertisingTermDAO;

/**
 * Servlet implementation class EditTermsServlet
 */
@WebServlet("/EditTermsServlet")
public class EditTermsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTermsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// DELETE TERM:
		if(!request.getParameter("termId").contentEquals(null)) {
			
			int termId = Integer.parseInt(request.getParameter("termId"));
			
			try {
				AdvertisingTermDAO atd = new AdvertisingTermDAO();
				
				atd.deleteRecord(termId);
				
				atd.closeResources();
				
				//Sweet Alert code
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
				out.println("<script>");
				out.println("$(document).ready(function() {");
				out.println("swal (\"Success\", \"Action complete. The Advertising term has successfully been deleted!\", \"success\")");
				out.println("});");
				out.println("</script>");
				//**********************************************************************************	
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//EDIT TERM:
		
		if(!request.getParameter("termId").contentEquals(null)) {
			
			int termId = Integer.parseInt(request.getParameter("termId"));
			
			String packageName = request.getParameter("packageName");
			int period = Integer.parseInt(request.getParameter("period"));
			double price = Double.parseDouble(request.getParameter("price"));
			
			AdvertisingTerm at;
			
			try {
				AdvertisingTermDAO atd = new AdvertisingTermDAO();
				
				//atd.addRecord(at);
				
				at = atd.returnRecord(termId);
				
				at.setPackageName(packageName);
				at.setPeriod(period);
				at.setPrice(price);
				
				atd.updateRecord(at);
				
				atd.closeResources();
				
				response.sendRedirect("/AdvertisingTermsServlet");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
