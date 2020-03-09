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

import model.BEANS.Admin;
import model.BEANS.Seller;
import model.DAO.*;

import java.sql.Date;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String errorMessage = "";
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		String strGender = request.getParameter("gender");
		char gender;
		
		if(strGender.equals(null)) {
			gender = Character.MIN_VALUE;
		}else {
			gender = strGender.charAt(0);
		}
		
		String oomangNo = request.getParameter("oomangNo");
		int contactNo = Integer.parseInt(request.getParameter("contactNo"));
		java.sql.Date dob = Date.valueOf(request.getParameter("dob"));
		String postalAddress = request.getParameter("postalAddress");
		String emailAddress = request.getParameter("emailAddress");
		
		String passwordToHash = request.getParameter("password");  // Hash Password
		String generatedPassword = null;
		
		// Check if email address is already in use:
		
		
		
		try {
			
			UserDAO ud = new UserDAO();
			
			if(ud.checkEmail(emailAddress) > 0) {
				//Check if email is already in use
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				errorMessage = "Error! Email address in use.";
				request.setAttribute("errorMessage", errorMessage);
				rd.forward(request, response);
			}else { //1995-04-13
								
				//Register User:
				//Seller seller = new Seller();
				
				Seller seller = new Seller();
				
				seller.setFirstName(firstName);
				seller.setLastName(lastName);				
				seller.setGender(gender);
				seller.setOomangNo(oomangNo);
				seller.setContactNo(contactNo);
				seller.setDob(dob);
				seller.setPostalAddress(postalAddress);
				seller.setEmailAddress(emailAddress);

				byte[] salt = getSalt();
				generatedPassword = getSecurePassword(passwordToHash, salt);
				seller.setPassword(generatedPassword);
				seller.setSalt(salt);
					
				ud.addRecord(seller);
				
				ud.closeResources();
				
				//Sweet Alert code
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
				out.println("<script>");
				out.println("$(document).ready(function() {");
				out.println("swal (\"Success\", \"Registration Successful. Login with your credentials!\", \"success\")");
				out.println("});");
				out.println("</script>");
				//**********************************************************************************	

				
				request.getRequestDispatcher("login.jsp").include(request, response);
				
			}
			
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
