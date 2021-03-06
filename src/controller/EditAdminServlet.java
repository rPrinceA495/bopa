package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.BEANS.Admin;
import model.BEANS.Seller;
import model.DAO.UserDAO;

/**
 * Servlet implementation class EditAdminServlet
 */
@WebServlet("/EditAdminServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,  // 10 MB 
maxFileSize=1024*1024*50,       // 50 MB
maxRequestSize=1024*1024*100)    // 100 MB
public class EditAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");

		
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
		java.sql.Date dob = Date.valueOf(request.getParameter("dob"));
		String postalAddress = request.getParameter("postalAddress");
		
		Part part = request.getPart("profilePic");
		String profilePic = "";
		
		
		/**
		 * Image Upload********************************************************************************
		 */
		
		final String UPLOAD_DIR = "app/uploads/profile_pics";
		
		// gets absolute path of the web application

        if(!(part.getSize() == 0)) {  
        	
        	String applicationPath = request.getServletContext().getRealPath("");
            
            // constructs path of the directory to save uploaded file
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
             
            // creates the save directory if it does not exists
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            
            System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
            
            String fileName = null;
            
            //Get part from request and write it to the file on server
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        	
        	profilePic = "../uploads/profile_pics/"+fileName;
    		
    		System.out.println(fileName + " File uploaded successfully!");
    		
        } else {
        	
        	// Keep the picture the same
        	
        	profilePic = admin.getProfilePic();
        	
        }
        	
    	try {
    			
    		UserDAO ud = new UserDAO();
    				
    		admin.setFirstName(firstName);
    		admin.setLastName(lastName);				
    		admin.setGender(gender);
    		admin.setOomangNo(oomangNo);
    		admin.setDob(dob);
    		admin.setPostalAddress(postalAddress);
    		admin.setProfilePic(profilePic);
    				
    		ud.updateRecord(admin);
    				
    		ud.closeResources();
    					
    		//Sweet Alert code
    		out.println("<meta http-equiv='refresh' content='1;URL=app/admin/index.jsp'>");//redirects after 3 seconds
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
			out.println("<script>");
			out.println("$(document).ready(function() {");
			out.println("swal (\"Success\", \"Profile successfully updated.\", \"success\")");
			out.println("});");
			out.println("</script>");		
			//**********************************************************************************	


    		} catch (ClassNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} 

		
	}
	
	/**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
    	
        String contentDisp = part.getHeader("content-disposition");
        
        System.out.println("content-disposition header= "+contentDisp);
        
        String[] tokens = contentDisp.split(";");
        
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
        
    }

}
