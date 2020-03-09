package model.BEANS;

import java.util.ArrayList;
import java.util.Calendar;

import model.DAO.ItemDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;


public class Seller extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sellerId, contactNo;
	private ArrayList<Item> items;
	
	
	/**
	 * Default constructor:
	 */
	public Seller() {
		super();
	}
	
	

	public Seller(int accId, int sellerId, String firstName, String lastName, String oomangNo, int contactNo, String postalAddress,
			String emailAddress, String password, String profilePic, String userType, Date dob, char gender,
			byte[] salt) {
		super(accId, firstName, lastName, oomangNo, postalAddress, emailAddress, password, profilePic, userType, dob, gender,
				salt);
		
		this.contactNo = contactNo;
		
	}

	/**
	 * *****************************************************GETTER AND SETTER METHODS**********************************************************
	 */
	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}


	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * *****************************************************BUSINESS LOGIC**********************************************************
	 */
	public void addItem(Item i) {
		
		try {
			
			ItemDAO id = new ItemDAO();
			
			id.addRecord(i);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void editItem(Item i) {
		
		try {
			
			ItemDAO id = new ItemDAO();
			
			id.updateRecord(i);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void extendItemListing(int itemId, AdvertisingTerm adTerm) { // Upon payment
		
		try {
			
			ItemDAO id = new ItemDAO();
			
			Property property = (Property) id.returnRecord(itemId);
			
			java.sql.Date expiryDate = property.getAdExpiryDate();
			
			Calendar c;
			
			// Today
			long millis=System.currentTimeMillis();  
			java.sql.Date today = new java.sql.Date(millis);  
			
			if(today.before(expiryDate)) {
				// Add package period to expiry date:
				
				c = Calendar.getInstance();
		        c.setTime(expiryDate);
		        c.add(Calendar.DATE, adTerm.getPeriod());
		        java.sql.Date newExpiryDate = new java.sql.Date(c.getTimeInMillis());
		        
		        id.updateExpiryDate(itemId, newExpiryDate);
				
			}else if(today.after(expiryDate)) {
				
				// Add package period to today:
				
				c = Calendar.getInstance();
		        c.setTime(today);
		        c.add(Calendar.DATE, adTerm.getPeriod());
		        java.sql.Date newExpiryDate = new java.sql.Date(c.getTimeInMillis());
		        
		        id.updateExpiryDate(itemId, newExpiryDate);
				
			}
			
		
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void flagItemSold(int itemId) {
		
		try {
			
			ItemDAO id = new ItemDAO();
			
			id.updateStatus(itemId, "sold");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	

	
}
