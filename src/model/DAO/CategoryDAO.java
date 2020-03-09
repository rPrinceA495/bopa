package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import model.BEANS.Category;
import model.BEANS.Subcategory;

public class CategoryDAO extends DAO<Category> {

	public CategoryDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addRecord(Category type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addRecordReturnKey(Category type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecord(Category type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category returnRecord(int key) {
		
		Category c = null;
		
		try{
        	
            sql = "SELECT * FROM item_category WHERE category_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, key);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	c = new Category();

            	//Retrieve info:
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                
                c.setCategoryId(categoryId);
                c.setCategoryName(categoryName);
                
            }

            return c;
        
        }catch(SQLException e) {System.out.println(e); return null;}
		
	}

	@Override
	public ArrayList<Category> returnRecords(int key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Category> returnCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteRecord(int key) {
		// TODO Auto-generated method stub
		return 0;
	}

	
		
	
}
