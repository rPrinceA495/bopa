package model.DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.BEANS.AdvertisingTerm;
import model.BEANS.Property;

/**USED TO POPULATE TERMS TABLE BY ADMIN. THIS DATA IS RETRIEVED BY GET 
 * AND PRESENTED TO THE USER WHEN ADDING PROPERTY. THE ONE THEY CHOOSE BILLS 
 * ACCORDINGLY AND EXTENDS AD EXPIRY DATE. cAN BE REQUEST SCOPED SENDING ID VALUE OF TERM TO PAYMENT SERVLET WHICH RETRIEVES TERN DETAILS AND BILLS ACCORDINGLY.
 * @author rpa495
 *
 */


public class AdvertisingTermDAO extends DAO<AdvertisingTerm> { 	
	

	public AdvertisingTermDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addRecord(AdvertisingTerm type) {
		
		try{
			
			/**
			 *  Add to admin table.
			 */
            sql = "INSERT into advertising_term (package_name, period, price) VALUES (?, ?, ?)";
                      
            pst = this.conn.prepareStatement(sql);
            
            
            pst.setString(1, type.getPackageName());
            pst.setInt(2, type.getPeriod());
            pst.setDouble(3, type.getPrice());
            
            
            pst.executeUpdate();
			rs=pst.getGeneratedKeys();
			
            System.out.println("Advertising Term added to the database");
            

        }catch(SQLException e) {System.out.println(e); }

		
	}

	@Override
	public int addRecordReturnKey(AdvertisingTerm type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecord(AdvertisingTerm type) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE advertising_term SET package_name = ?, period = ?, price = ? WHERE item_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, type.getPackageName());
	        pst.setInt(2, type.getPeriod());
	        pst.setDouble(3, type.getPrice());

	        rowsAffected = pst.executeUpdate();
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
		
	}

	@Override
	public AdvertisingTerm returnRecord(int key) {
		
		AdvertisingTerm at = null;
		
		try{
        	
            sql = "SELECT * FROM advertising_term WHERE term_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, key);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	at = new AdvertisingTerm();
            	
            	//Retrieve info:
                int termId = rs.getInt("term_id");
                String packageName = rs.getString("package_name");
                int period = rs.getInt("period");
                Double price = rs.getDouble("price");
                String packageDetails = packageName + " (P"+ String.format("%.2f", price)+")";  
                
                at.setTermId(termId);
                at.setPackageName(packageName);
                at.setPeriod(period);
                at.setPrice(price);
                at.setPackageDetails(packageDetails);
                
            }

            return at;
        
        }catch(SQLException e) {System.out.println(e); return null;}

		
	}

	@Override
	public ArrayList<AdvertisingTerm> returnRecords(int key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<AdvertisingTerm> returnTerms() {	
		
		AdvertisingTerm at = null;
		
		ArrayList<AdvertisingTerm> list = new ArrayList<>();
		
		try{
          
            st = conn.createStatement();
            
            sql = "SELECT * FROM advertising_term";
            
            //Execute the query
            rs = st.executeQuery(sql);
            
            //Extract from result set:
            while(rs.next()) {
            	
            	at = new AdvertisingTerm();
            	
            	//Retrieve info:
                int termId = rs.getInt("term_id");
                String packageName = rs.getString("package_name");
                int period = rs.getInt("period");
                Double price = rs.getDouble("price");
                String packageDetails = packageName + " (P"+ String.format("%.2f", price)+")";  
                
                at.setTermId(termId);
                at.setPackageName(packageName);
                at.setPeriod(period);
                at.setPrice(price);
                at.setPackageDetails(packageDetails);
                
                list.add(at);
                
            }
            
            

            return list;
        
        }catch(SQLException e) {System.out.println(e); return null;}
		
	}

	@Override
	public int deleteRecord(int key) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "DELETE FROM advertising_term WHERE term_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, key);
	        

			rowsAffected = pst.executeUpdate();

        
        }catch(SQLException e) {System.out.println(e);}
		
		
		return rowsAffected;	
		
	}

	

	
	
	
	
}
