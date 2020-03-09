package model.BEANS;

import java.io.Serializable;
import java.sql.Date;

public class Property extends Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String location;

	public Property() {
		super();
	}

	
	public Property(int itemId, Seller seller, Category category, Subcategory subcategory, double price,
			String listedFor, String description, boolean paymentMade, Date adExpiryDate, Date paymentDate,
			String image) {
		super(itemId, seller, category, subcategory, price, listedFor, description, paymentMade, adExpiryDate, paymentDate,
				image);
		// TODO Auto-generated constructor stub
	}









	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	
	
	
}