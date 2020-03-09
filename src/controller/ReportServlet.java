package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.ItemDAO;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		
		ItemDAO id;
		
		//int countSale, countRent, countRes, countInd, countCom, countLan, countAct, countExp, countSold;
		
		HashMap<String, Integer> listedForCount = new HashMap<>();
		
		HashMap<String, Integer> typeCount= new HashMap<>();
		
		HashMap<String, Integer> statusCount = new HashMap<>();
		
		try {
			id = new ItemDAO();
			
			// Listed For
			listedForCount.put("Sale", id.countListedFor("Sale"));
			listedForCount.put("Rent", id.countListedFor("Rent"));
			
			typeCount.put("res", id.countType(1));
			typeCount.put("com", id.countType(2));
			typeCount.put("ind", id.countType(3));
			typeCount.put("lan", id.countType(4));
			
			statusCount.put("expired", id.countStatus("expired"));
			statusCount.put("active", id.countStatus("active"));
			statusCount.put("sold", id.countStatus("sold"));
			
			id.closeResources();
			
			session.setAttribute("listedForCount", listedForCount);
			session.setAttribute("typeCount", typeCount);
			session.setAttribute("statusCount", statusCount);
			
			response.sendRedirect("app/admin/reports.jsp");
			
			
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
