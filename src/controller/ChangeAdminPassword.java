package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

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
 * Servlet implementation class ChangeAdminPassword
 */
@WebServlet("/ChangeAdminPassword")
public class ChangeAdminPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAdminPassword() {
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		User admin = (Admin) session.getAttribute("admin");

		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");

		String generatedPassword = null;
		
		try {
			
			 UserDAO ud = new UserDAO();
		       
			 // Get salt from database:
		     byte[] salt = ud.getPasswordSalt(admin.getEmailAddress());
			
   	
		     generatedPassword = getSecurePassword(currentPassword, salt);
			        
		     // If correct change current password
		     if(generatedPassword.equals(admin.getPassword())) {
		        		
				     // Update password:
				     byte[] newSalt = getSalt();
				     generatedPassword = getSecurePassword(newPassword, newSalt);
				        		
				     int success = ud.changePassword(admin.getAccId(), generatedPassword, newSalt);
				     
				     
				     if(success > 0) {
						
					    //Sweet Alert code
						out.println("<meta http-equiv='refresh' content='1;URL=app/admin/index.jsp'>");
						out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
						out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
						out.println("<script>");
						out.println("$(document).ready(function() {");
						out.println("swal (\"Success\", \"Password successfully changed.\", \"success\")");
						out.println("});");
						out.println("</script>");		
						//**********************************************************************************
						        			
						    
				     }
				     
		        		
		       	} else {
		        		
		
		        		//Sweet Alert code
					    out.println("<meta http-equiv='refresh' content='1;URL=app/admin/index.jsp'>");
						out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
						out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
						out.println("<script>");
						out.println("$(document).ready(function() {");
						out.println("swal (\"Error\", \"Incorrect password entered.\", \"error\")");
						out.println("});");
						out.println("</script>");		
						//**********************************************************************************	 
		        			
		        		

		        	}
			        
			        ud.closeResources();
			        
		        
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
	}
	
	/**
	 * CODE TO HASH PASSWORD FOR DATABASE ENTRY:*********************************************************************************************
	 */
    private String getSecurePassword(String passwordToHash, byte[] salt) // maybe static
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
     
    //Add salt
    private byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException // maybe static
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
	/**
	 *****************************************************************************************************************************************
	 */	


}
