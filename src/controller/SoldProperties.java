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
import model.DAO.ItemDAO;

/**
 * Servlet implementation class SoldProperties
 */
@WebServlet("/SoldProperties")
public class SoldProperties extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoldProperties() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		
		ItemDAO id;
		
		try {
			id = new ItemDAO();
			
			ArrayList<Item> soldProperties = id.returnSoldProperties();
			
			id.closeResources();
			
			session.setAttribute("soldProperties", soldProperties);
			
			response.sendRedirect("app/admin/sold_properties.jsp");
			
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
