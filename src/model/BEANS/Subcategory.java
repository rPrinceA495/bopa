package model.BEANS;

import java.io.Serializable;

public class Subcategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int subcategoryId, categoryId;
	private String subcategoryName;
	
	public Subcategory() {}
	
	public Subcategory(int subcategoryId, int categoryId, String subcategoryName) {
		super();
		this.subcategoryId = subcategoryId;
		this.categoryId = categoryId;
		this.subcategoryName = subcategoryName;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	
	
	
	

}
