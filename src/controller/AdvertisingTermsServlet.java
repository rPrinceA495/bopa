package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BEANS.AdvertisingTerm;
import model.DAO.AdvertisingTermDAO;

/**
 * Servlet implementation class AdvertisingTermsServlet
 */
@WebServlet("/AdvertisingTermsServlet")
public class AdvertisingTermsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertisingTermsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		
		
		//Get all current advertising terms and redirect to jsp:
		try {
			
			AdvertisingTermDAO atd = new AdvertisingTermDAO();
			
			ArrayList<AdvertisingTerm> list = atd.returnTerms();
			
			atd.closeResources();
			
			session.setAttribute("terms", list);
			
			response.sendRedirect("app/admin/advertising_terms.jsp");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		ArrayList<AdvertisingTerm> list = (ArrayList<AdvertisingTerm>) session.getAttribute("terms");
		
		String packageName = request.getParameter("packageName");
		int period = Integer.parseInt(request.getParameter("period"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		AdvertisingTerm at = new AdvertisingTerm();
		
		at.setPackageName(packageName);
		at.setPeriod(period);
		at.setPrice(price);
		
		try {
			AdvertisingTermDAO atd = new AdvertisingTermDAO();
			
			list.add(at);
			atd.addRecord(at);
			atd.closeResources();

			//Sweet Alert code
    		out.println("<meta http-equiv='refresh' content='1;URL=app/admin/advertising_terms.jsp'>");//redirects after 3 seconds
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
			out.println("<script>");
			out.println("$(document).ready(function() {");
			out.println("swal (\"Success\", \"Terms successfully updated.\", \"success\")");
			out.println("});");
			out.println("</script>");		
			//**********************************************************************************	
			
			
			//response.sendRedirect("app/admin/advertising_terms.jsp");
			
			//doGet(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
