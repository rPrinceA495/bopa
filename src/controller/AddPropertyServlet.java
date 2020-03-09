package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.BEANS.Item;
import model.BEANS.Property;
import model.BEANS.Seller;
import model.BEANS.Subcategory;
import model.DAO.ItemDAO;
import model.DAO.SubcategoryDAO;
import model.DAO.UserDAO;

/**
 * Servlet implementation class AddPropertyServlet
 */
@WebServlet("/AddPropertyServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,  // 10 MB 
maxFileSize=1024*1024*50,       // 50 MB
maxRequestSize=1024*1024*100)    // 100 MB
public class AddPropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPropertyServlet() {
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
		
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("seller");
		
		Item property = new Property();
		int subcategoryId = Integer.parseInt(request.getParameter("subcategoryId"));
		
		String listedFor = request.getParameter("listedFor");
		String location = request.getParameter("location");
		
		String description = request.getParameter("description");
		
		double price = Double.parseDouble(request.getParameter("price"));
		
		String priceFormat = String.format("%.2f",price);;
		
		//Today's date
		long time = System.currentTimeMillis();
		java.sql.Date adExpiryDate = new Date(time);
		
		Part part = request.getPart("photo");
		String photo = "";
		
		
		/**
		 * Image Upload********************************************************************************
		 */
		
		final String UPLOAD_DIR = "app/uploads/properties";
		
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
        	
        	photo = "../uploads/properties/"+fileName;
    		
    		System.out.println(fileName + " File uploaded successfully!");
    		
        } else {
        	
        	// Keep the picture the same
        	
        	photo = "../uploads/properties/house-308936_640.png"; // Default
        	
        }
        	
    	try {
    			
    		ItemDAO id = new ItemDAO();
    				
    		property.setSeller(seller);
    		
    		SubcategoryDAO scd = new SubcategoryDAO();
            Subcategory subcategory = scd.returnRecord(subcategoryId);
            property.setSubcategory(subcategory);			
    		
    		property.setListedFor(listedFor);
    		property.setPrice(price);
    		property.setPriceFormat(priceFormat);
    		property.setAdExpiryDate(adExpiryDate);
    		property.setStatus("");
    		
    		((Property) property).setLocation(location);
    		
    		property.setImage(photo);
    		
    		property.setDescription(description);
    		
    		int primaryKey = id.addRecordReturnKey(property);
    		
    		property.setItemId(primaryKey);
    		
    		seller.getItems().add(property);
    		
    					
    		//Sweet Alert code
    		out.println("<meta http-equiv='refresh' content='1;URL=app/seller/manage.jsp'>");//redirects after 3 seconds
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"); 
			out.println("<script>");
			out.println("$(document).ready(function() {");
			out.println("swal (\"Success\", \"Your property has successfully been added.\", \"success\")");
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
