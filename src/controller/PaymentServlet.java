package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BEANS.AdvertisingTerm;
import model.BEANS.Item;
import model.BEANS.Seller;
import model.DAO.AdvertisingTermDAO;
import model.DAO.ItemDAO;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
    	
    	
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//RequestDispatcher rd = request.getRequestDispatcher(arg0);
		
		int itemIdToExtend = Integer.parseInt(request.getParameter("itemId"));	
		
		AdvertisingTermDAO atd;
		try {
			
			atd = new AdvertisingTermDAO();
			
			ArrayList<AdvertisingTerm> list = atd.returnTerms();
			
			atd.closeResources();
			
			session.setAttribute("terms", list);
			session.setAttribute("itemIdToExtend", itemIdToExtend);
			
			response.sendRedirect("app/seller/secure_payment.jsp?");
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("seller");
		
		int itemIdToExtend = Integer.parseInt(request.getParameter("itemIdToExtend"));
		
		//int itemId = Integer.parseInt(request.getParameter("itemId"));	
		int termId = Integer.parseInt(request.getParameter("termId"));
		
		
		//String cardNumber = request.getParameter("cardNumber");
		//String expirationDate = request.getParameter("expirationDate");
		//String check = request.getParameter("check");
		//String naeOnCard = request.getParameter("nameOnCard");
			
		
		// Logic to to bill accordingly and change state of item to payment_made true. 
		
		try {
			
			
			ItemDAO id = new ItemDAO();
			
			AdvertisingTermDAO atd = new AdvertisingTermDAO();
			AdvertisingTerm at = atd.returnRecord(termId);
			
			int period = at.getPeriod();
			
			int index = id.getItemIndex(seller.getItems(), itemIdToExtend);
	
			Item item = seller.getItems().get(index);
			
			// Calculate new expiry date with Calendar object
			java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
			java.sql.Date adExpiryDate = item.getAdExpiryDate();
			java.sql.Date newAdExpiryDate = null;
			
			if(todaysDate.before(adExpiryDate)) {
				
				// Add period to old expiry date
				newAdExpiryDate = addDays(adExpiryDate, period);
				
			}else if(todaysDate.equals(adExpiryDate) || todaysDate.after(adExpiryDate)) {
				// Add period to today
				newAdExpiryDate = addDays(todaysDate, period);
				
			}
			
			item.setStatus("");		
			item.setPaymentMade(true);
			item.setPaymentDate(todaysDate);
			item.setAdExpiryDate(newAdExpiryDate);
					
			id.updateStatus(itemIdToExtend, "");
			id.updatePaymentMade(itemIdToExtend, true);
			id.updatePaymentDate(itemIdToExtend, todaysDate);
			id.updateExpiryDate(itemIdToExtend, newAdExpiryDate);
			
			
			
			//Sweet Alert code
    		out.println("<meta http-equiv='refresh' content='1;URL=app/seller/manage.jsp'>");//redirects after 3 seconds
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
			out.println("<script>");
			out.println("$(document).ready(function() {");
			out.println("swal (\"Transaction Successfull\", \"Your property listing has been extended.\", \"success\")");
			out.println("});");
			out.println("</script>");		
			//**********************************************************************************
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		// When admin accepts payment and lists item, payment made changes to false. Payment made guides what admin will see.
	}
	
	public static java.sql.Date addDays(java.sql.Date date, int days) {
		
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new java.sql.Date(c.getTimeInMillis());
        
    }
	

}
