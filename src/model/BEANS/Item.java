package model.BEANS;

import java.io.Serializable;
import java.sql.Date;

public abstract class Item implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int itemId;
	protected Seller seller;
	protected Category category;
	protected Subcategory subcategory;
	protected double price;
	protected String listedFor, description, priceFormat, status, image;
	protected boolean paymentMade;
	protected Date adExpiryDate, paymentDate;
	
	public Item() {
		this.image = "../uploads/properties/house-308936_1280.png"; // Default
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Item(int itemId, Seller seller, Category category, Subcategory subcategory, double price,
			String listedFor, String description, boolean paymentMade,
			Date adExpiryDate, Date paymentDate,String image) {
		super();
		this.itemId = itemId;
		this.seller = seller;
		this.category = category;
		this.subcategory = subcategory;
		this.price = price;
		this.listedFor = listedFor;
		this.description = description;
		this.paymentMade = paymentMade;
		this.adExpiryDate = adExpiryDate;
		this.paymentDate = paymentDate;
		this.image = image;
	}
	
	

	public boolean isPaymentMade() {
		return paymentMade;
	}

	public void setPaymentMade(boolean paymentMade) {
		this.paymentMade = paymentMade;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	
	
	
	public String getPriceFormat() {
		return priceFormat;
	}

	public void setPriceFormat(String priceFormat) {
		this.priceFormat = priceFormat;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getListedFor() {
		return listedFor;
	}

	public void setListedFor(String listedFor) {
		this.listedFor = listedFor;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Date getAdExpiryDate() {
		return adExpiryDate;
	}

	public void setAdExpiryDate(Date adExpiryDate) {
		this.adExpiryDate = adExpiryDate;
	}
	
	

}
