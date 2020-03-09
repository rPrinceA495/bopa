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

import model.BEANS.Item;
import model.DAO.ItemDAO;

/**
 * Servlet implementation class ListPropServlet
 */
@WebServlet("/ListPropServlet")
public class ListPropServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPropServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ArrayList<Item> extendedProperties = (ArrayList<Item>) session.getAttribute("extendedProperties");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ItemDAO id;
		
		
		
		try {
			
			id = new ItemDAO();
			
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			
			int index = id.getItemIndex(extendedProperties, itemId);
			
			Item item = extendedProperties.get(index);
			
			item.setStatus("active");
			item.setPaymentMade(false);;
			
			id.updateStatus(itemId, "active");
			id.updatePaymentMade(itemId, false);
			
			id.closeResources();
			
			//Sweet Alert code
    		out.println("<meta http-equiv='refresh' content='1;URL=app/admin/listings_management.jsp'>");//redirects after 3 seconds
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
			out.println("<script>");
			out.println("$(document).ready(function() {");
			out.println("swal (\"Success\", \"Property has been added to listings.\", \"success\")");
			out.println("});");
			out.println("</script>");		
			//**********************************************************************************
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
