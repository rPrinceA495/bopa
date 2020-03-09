package model.BEANS;

import java.io.Serializable;

public class AdvertisingTerm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int termId, period;
	private double price;
	private String packageName;
	private String packageDetails;
	
	public String getPackageDetails() {
		return packageDetails;
	}

	public void setPackageDetails(String packageDetails) {
		this.packageDetails = packageDetails;
	}

	public AdvertisingTerm() {}
	
	public AdvertisingTerm(int termId, int period, double price, String packageName) {
		super();
		this.termId = termId;
		this.period = period;
		this.price = price;
		this.packageName = packageName;
	}


	public int getTermId() {
		return termId;
	}


	public void setTermId(int termId) {
		this.termId = termId;
	}


	public int getPeriod() {
		return period;
	}


	public void setPeriod(int period) {
		this.period = period;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
	

}
