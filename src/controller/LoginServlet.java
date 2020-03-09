package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BEANS.Admin;
import model.BEANS.Seller;
import model.BEANS.User;
import model.DAO.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errorMessage = "";
		
		String emailAddress = request.getParameter("emailAddress");
		String passwordToHash = request.getParameter("password");
		String generatedPassword = null;
		
		int accId;

		
		
		try {
  
	        UserDAO ud = new UserDAO();
	        
	        byte[] salt = ud.getPasswordSalt(emailAddress);
	        
	        // If salt exists:
	        if(!salt.equals(null)) {
	        	
	        	generatedPassword = getSecurePassword(passwordToHash, salt);
		        accId = ud.validateUser(emailAddress, generatedPassword);
		        
		        if(accId > 0) {		        	
		        	
		        	User user =  ud.getUser(accId);
					
					if(user instanceof Admin) {
						
						Admin admin = ud.getAdmin((Admin) user);
						
						ud.closeResources();
						
						HttpSession session = request.getSession();
						session.setAttribute("admin", admin);
						
						response.sendRedirect("app/admin/");
						
					}else if(user instanceof Seller) {
						
						Seller seller = ud.getSeller((Seller) user);
						
						ud.closeResources();
						
						HttpSession session = request.getSession();
						session.setAttribute("seller", seller);
						
						response.sendRedirect("app/seller/");
						
					}else {
						
						ud.closeResources();
						
						response.sendRedirect("login.jsp");
						
					}
					
					
		        	

		        }else {
		        	
		        	ud.closeResources();
		        	
		        	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		        	errorMessage = "Error! Invalid credentials";
					request.setAttribute("errorMessage", errorMessage);
					rd.forward(request, response);
		        }
		        
	        }else {
	        	
	        	ud.closeResources();
	        	
	        	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	        	errorMessage = "Error! Invalid credentials";
				request.setAttribute("errorMessage", errorMessage);
				rd.forward(request, response);
	        }
	        
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
        
		
		
	}
	
	
	/**
	 * CODE TO HASH PASSWORD FOR DATABASE ENTRY:*********************************************************************************************
	 */
    private static String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
	

}
