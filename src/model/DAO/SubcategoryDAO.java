package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import model.BEANS.AdvertisingTerm;
import model.BEANS.Category;
import model.BEANS.Subcategory;

public class SubcategoryDAO extends DAO<Subcategory>{

	public SubcategoryDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addRecord(Subcategory type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addRecordReturnKey(Subcategory type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecord(Subcategory type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Subcategory returnRecord(int key) {
		
		Subcategory s = null;
		
		try{
        	
            sql = "SELECT * FROM item_subcategory WHERE subcategory_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, key);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	s = new Subcategory();

            	//Retrieve info:
                int subcategoryId = rs.getInt("subcategory_id");
                int categoryId = rs.getInt("category_id");
                String subcategoryName = rs.getString("subcategory_name");
                
                s.setSubcategoryId(subcategoryId);
                s.setCategoryId(categoryId);
                s.setSubcategoryName(subcategoryName);
                
            }

            return s;
        
        }catch(SQLException e) {System.out.println(e); return null;}
		
	}

	@Override
	public ArrayList<Subcategory> returnRecords(int key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Subcategory> returnSubcategories() {
		
		Subcategory s;
		
		ArrayList<Subcategory> list = new ArrayList<>();
		
		try{
          
            st = conn.createStatement();
            
            sql = "SELECT * FROM item_subcategory";
            
            //Execute the query
            rs = st.executeQuery(sql);
            
            //Extract from result set:
            while(rs.next()) {
            	
            	s = new Subcategory();

            	//Retrieve info:
                int subcategoryId = rs.getInt("subcategory_id");
                int categoryId = rs.getInt("category_id");
                String subcategoryName = rs.getString("subcategory_name");
                
                s.setSubcategoryId(subcategoryId);
                s.setCategoryId(categoryId);
                s.setSubcategoryName(subcategoryName);
                
                list.add(s);
                
            }
            
            

            return list;
        
        }catch(SQLException e) {System.out.println(e); return null;}
		
	}

	@Override
	public int deleteRecord(int key) {
		// TODO Auto-generated method stub
		return 0;
	}

}
