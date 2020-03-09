package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BEANS.Item;
import model.BEANS.Seller;
import model.BEANS.Subcategory;
import model.BEANS.User;
import model.DAO.ItemDAO;
import model.DAO.SubcategoryDAO;

/**
 * Servlet implementation class ManagePropertiesServlet
 */
@WebServlet("/ManagePropertiesServlet")
public class ManagePropertiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagePropertiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("seller");
		
		try {
			
			// Get the subcategories for dropdown list:
			SubcategoryDAO scd = new SubcategoryDAO();
			ArrayList<Subcategory> subcategories = scd.returnSubcategories();
			
			session.setAttribute("subcategories", subcategories);
			
			// Get properties belonging to seller:
			ItemDAO id = new ItemDAO();
			ArrayList<Item> properties = id.returnRecords(seller.getSellerId());
			seller.setItems(properties);
			
			//session.setAttribute("seller", seller);
			
						
			response.sendRedirect("app/seller/manage.jsp");
			
			
		} catch (ClassNotFoundException e) {
			
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
