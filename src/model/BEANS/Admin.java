package model.BEANS;

import java.sql.Date;
import java.util.ArrayList;

import model.DAO.ItemDAO;

public class Admin extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int adminId;
	
	public Admin() {}
	
	public Admin(int accId, int adminId, String firstName, String lastName, String oomangNo, String postalAddress,
			String emailAddress, String password, String profilePic, String userType, Date dob, char gender,
			byte[] salt) {
		super(accId, firstName, lastName, oomangNo, postalAddress, emailAddress, password, profilePic, userType, dob, gender,
				salt);
		
		this.adminId = adminId;
		
	}




	/**
	 * *****************************************************GETTER AND SETTER METHODS**********************************************************
	 */
	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	
	
	/**
	 * *****************************************************BUSINESS LOGIC**********************************************************
	 */

	public void listItem(int itemId) {
		
		try {
			
			ItemDAO id = new ItemDAO();
			
			id.updateStatus(itemId, "active");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	


	public void blacklistItem(int itemId) {
		
		try {
			
			ItemDAO id = new ItemDAO();
			
			id.updateStatus(itemId, "blacklisted");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

}
