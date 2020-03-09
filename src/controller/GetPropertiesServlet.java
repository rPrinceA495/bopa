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
 * Servlet implementation class GetPropertiesServlet
 */
@WebServlet("/GetPropertiesServlet")
public class GetPropertiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPropertiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int subcategoryId = Integer.parseInt(request.getParameter("subcategoryId"));
		
		ArrayList<Item> listedProperties;
		
		ItemDAO id;
		
		try {
			
			id = new ItemDAO();
			
			listedProperties = new ArrayList<>();
			
			long time = System.currentTimeMillis();
			
			java.sql.Date today = new java.sql.Date(time);
			
			listedProperties = id.returnActiveProperties(subcategoryId, today);
			
			id.closeResources();
			
			session.setAttribute("listedProperties", listedProperties);
			
			if(subcategoryId == 1) {
				
				response.sendRedirect("app/buyer/index.jsp");
				
			}else if(subcategoryId == 2) {
				
				response.sendRedirect("app/buyer/commercial.jsp");
				
			}else if(subcategoryId == 3) {
				
				response.sendRedirect("app/buyer/industrial.jsp");
				
			}else if(subcategoryId == 4) {
				
				response.sendRedirect("app/buyer/land.jsp");
				
			}
			
			

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
