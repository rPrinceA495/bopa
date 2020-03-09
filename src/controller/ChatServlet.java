package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BEANS.Admin;
import model.BEANS.ChatThread;
import model.BEANS.Seller;
import model.BEANS.User;
import model.DAO.ChatMessageDAO;
import model.DAO.ChatThreadDAO;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("seller");
		
	
		try {
			
			ChatThreadDAO ctd = new ChatThreadDAO();

			// Get the user's message threads (All they are involved in & encapsulate getting messgaes for each thread)
			ArrayList<ChatThread> threads = ctd.returnRecords(user.getAccId());
			
			user.setThreads(threads); 

			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		// Send Messages
		int receipientId = Integer.parseInt(request.getParameter("receipientId"));
		String msgContent = request.getParameter("msgContent");

		
		if(request.getParameter("button").contentEquals("send")) {
			
			boolean sent = user.sendMessage(user.getAccId(), receipientId, msgContent);
			
			if(sent) {
				//Tell user that it was sent
			}else {
				// Message not sent please try again
			}
			
		}
		
		
		
	}

}
