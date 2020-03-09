package model.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.BEANS.Admin;
import model.BEANS.Category;
import model.BEANS.Item;
import model.BEANS.Property;
import model.BEANS.Seller;
import model.BEANS.Subcategory;

public class ItemDAO extends DAO<Item> {

	public ItemDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addRecord(Item type) {

		if (type instanceof Property) {

			
			try {
				/**
				 *  Add to item table.
				 */
	            sql = "INSERT into item (seller_id, subcategory_id, listed_for, price, description, ad_expiry_date, location, status, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                      
	            pst = conn.prepareStatement(sql);
	            
	            pst.setInt(1, type.getSeller().getSellerId());
	            pst.setInt(2, type.getSubcategory().getSubcategoryId()); // Select list with values 1,2,3,4
	            pst.setString(3, type.getListedFor()); // Select list with values 1,2,3,4 "Rent/Sale"
	            pst.setDouble(4, type.getPrice());
	            pst.setString(5, type.getDescription());
	            pst.setDate(6, type.getAdExpiryDate()); //Is current date
	            pst.setString(7, ((Property) type).getLocation());
	            pst.setString(8, "");
	            pst.setString(9, type.getImage());
	            
	            pst.executeUpdate();

	            System.out.println("Property added to the database");
	            

	        }catch(SQLException e) {System.out.println(e); }

		}

	}

	@Override
	public int updateRecord(Item type) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE item SET subcategory_id = ?, listed_for = ?, price = ?, description = ?, location = ?,image = ? WHERE item_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, type.getSubcategory().getSubcategoryId());
	        pst.setString(2, type.getListedFor());
	        pst.setDouble(3, type.getPrice());
	        pst.setString(4, type.getDescription());
	        pst.setString(5, ((Property) type).getLocation());
	        pst.setString(6, type.getImage());
	        pst.setInt(7, type.getItemId());

	        rowsAffected = pst.executeUpdate();
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
	}

	
	/**
		*First use values from form request to mutate record the mutate session object. 
		*pass in the key to update each column with all columns whether mutated or not. 
		*Hence to check expired method simply mutates expired attribute and updates or saves the new state. 
		**/
	@Override
	public Item returnRecord(int key) {     
		
		Item item = null;
		
		try{
			
			//System.out.println("getting 1...");
        	
            sql = "SELECT * FROM item WHERE item_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, key);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {
            	
            	item = new Property();

            	//Retrieve info:
                int itemId = rs.getInt("item_id");
                int sellerId = rs.getInt("seller_id");
                int categoryId = rs.getInt("category_id");
                int subcategoryId = rs.getInt("subcategory_id");
                double price = rs.getDouble("price");
                String priceFormat = String.format("%.2f",price);
                String listedFor = rs.getString("listed_for");
                String location = rs.getString("location");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String image = rs.getString("image");
                boolean paymentMade = rs.getBoolean("payment_made");
                java.sql.Date adExpiryDate = rs.getDate("ad_expiry_date");
                java.sql.Date paymentDate = rs.getDate("payment_date");
                
                item.setItemId(itemId);
                
                
                UserDAO ud = new UserDAO();
                Seller seller = (Seller) ud.returnRecord(sellerId);
                item.setSeller(seller);
                
                System.out.println("Item retrieved!");

                CategoryDAO cd = new CategoryDAO();
                Category category = cd.returnRecord(categoryId);
                cd.closeResources();
                item.setCategory(category);  // categoryDAO takes care of this
                
                SubcategoryDAO scd = new SubcategoryDAO();
                Subcategory subcategory = scd.returnRecord(subcategoryId);
                scd.closeResources();
                item.setSubcategory(subcategory);  // categoryDAO takes care of this
                
                item.setPrice(price);
                item.setPriceFormat(priceFormat);
                item.setListedFor(listedFor);
                item.setDescription(description);
                ((Property) item).setLocation(location);
                item.setImage(image);
                item.setStatus(status);
                item.setPaymentMade(paymentMade);
                item.setAdExpiryDate(adExpiryDate);
                item.setPaymentDate(paymentDate);
                
                System.out.println("Item retrieved!");

                
            }

            return item;
        
        }catch(SQLException | ClassNotFoundException e) {System.out.println(e); return null;}

		
	}

	@Override
	public int deleteRecord(int key) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "DELETE FROM item WHERE item_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, key);
	        

			rowsAffected = pst.executeUpdate();

        
        }catch(SQLException e) {System.out.println(e);}
		
		
		return rowsAffected;	
		
	}


	/** This method returns all the records that belong to a specific seller:
	 * 
	 */
	@Override
	public ArrayList<Item> returnRecords(int key) {
		
		Property item = null;
		ArrayList<Item> properties = new ArrayList<>();
		
		try{
        	
            sql = "SELECT * FROM item WHERE seller_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, key);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	item = new Property();

            	//Retrieve info:
                int itemId = rs.getInt("item_id");
                int sellerId = rs.getInt("seller_id");
                int categoryId = rs.getInt("category_id");
                int subcategoryId = rs.getInt("subcategory_id");
                double price = rs.getDouble("price");
                String priceFormat = String.format("%.2f",price);
                String listedFor = rs.getString("listed_for");
                String location = rs.getString("location");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String image = rs.getString("image");
                boolean paymentMade = rs.getBoolean("payment_made");
                java.sql.Date adExpiryDate = rs.getDate("ad_expiry_date");
                java.sql.Date paymentDate = rs.getDate("payment_date");
                
                item.setItemId(itemId);
                
                
                UserDAO ud = new UserDAO();
                Seller seller = (Seller) ud.returnRecord(sellerId);
                item.setSeller(seller);
                

                CategoryDAO cd = new CategoryDAO();
                Category category = cd.returnRecord(categoryId);
                cd.closeResources();
                item.setCategory(category);  // categoryDAO takes care of this
                
                SubcategoryDAO scd = new SubcategoryDAO();
                Subcategory subcategory = scd.returnRecord(subcategoryId);
                scd.closeResources();
                item.setSubcategory(subcategory);  // subcategoryDAO takes care of this
                
                item.setPrice(price);
                item.setPriceFormat(priceFormat);
                item.setListedFor(listedFor);
                item.setDescription(description);
                ((Property) item).setLocation(location);
                item.setImage(image);
                item.setStatus(status);
                item.setPaymentMade(paymentMade);
                item.setAdExpiryDate(adExpiryDate);
                item.setPaymentDate(paymentDate);

                
                properties.add(item);

                
            }

            return properties;
        
        }catch(SQLException | ClassNotFoundException e) {System.out.println(e); return null;}
		
	}
	
	/** Save  into a hash map to be displayed in report view
	 * 
	 * @param listedFor
	 * @return
	 */
	public int countListedFor(String listedFor) {
		int count = 0;
		
		
		try{
        	
            sql = "SELECT COUNT(listed_for) AS x FROM item WHERE listed_for = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, listedFor);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	count = rs.getInt("x");
                
            }

            return count;
        
        }catch(SQLException e) {System.out.println(e); return count;}
	}
	
	public int countType(int subcategoryId) {
		int count = 0;
		
		
		try{
        	
            sql = "SELECT COUNT(subcategory_id) AS x FROM item WHERE subcategory_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, subcategoryId);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	count = rs.getInt("x");
                
            }

            return count;
        
        }catch(SQLException e) {System.out.println(e); return count;}
	}
	
	
	public int countStatus(String status) {
		int count = 0;
		
		
		try{
        	
            sql = "SELECT COUNT(status) AS x FROM item WHERE status = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, status);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	count = rs.getInt("x");
                
            }

            return count;
        
        }catch(SQLException e) {System.out.println(e); return count;}
	}
	
	
	/**
	 * 
	 * @param date: 5 days from the current date. (Conicides with expiry date in sql so a reminder is sent)
	 * @return emails of sellers whose items are expiring
	 */
	public ArrayList<String> getReminderEmails(java.sql.Date date) {
		
		ArrayList<String> emails = new ArrayList<>();
		
		try{
        	
            sql = "SELECT seller_id FROM item WHERE ad_expiry_date = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setDate(1, date);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	int sellerId = rs.getInt("seller_id");

            	//Retrieve info:

                UserDAO ud = new UserDAO();
                Seller seller = (Seller) ud.returnRecord(sellerId);
                
                emails.add(seller.getEmailAddress());
                
            }

            return emails;
        
        }catch(SQLException | ClassNotFoundException e) {System.out.println(e); return null;}
		
	}
	
	/**
	 * Returns Items whose expiry date is the current day.
	 * @param todaysDate
	 * @return
	 */
	public ArrayList<Integer> getExpiringPropertyIds(java.sql.Date todaysDate) {
		
		ArrayList<Integer> expiringPropertyIds = new ArrayList<>();
		
		try{
        	
            sql = "SELECT item_id FROM item WHERE ad_expiry_date = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setDate(1, todaysDate);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	int itemId = rs.getInt("item_id");
                
            	expiringPropertyIds.add(itemId);
                
            }

            return expiringPropertyIds;
        
        }catch(SQLException e) {System.out.println(e); return null;}
		
	}
	
	

	
	/** This method returns properties for the admin to review before listing (Properties just paid for)
	 * 
	 */
	public ArrayList<Item> returnExtendedProperties() {
		
		Property item = null;
		ArrayList<Item> properties = new ArrayList<>();
		
		try{
        	
            sql = "SELECT * FROM item WHERE payment_made = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setBoolean(1, true);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	item = new Property();

            	//Retrieve info:
                int itemId = rs.getInt("item_id");
                int sellerId = rs.getInt("seller_id");
                int categoryId = rs.getInt("category_id");
                int subcategoryId = rs.getInt("subcategory_id");
                double price = rs.getDouble("price");
                String priceFormat = String.format("%.2f",price);
                String listedFor = rs.getString("listed_for");
                String location = rs.getString("location");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String image = rs.getString("image");
                boolean paymentMade = rs.getBoolean("payment_made");
                java.sql.Date adExpiryDate = rs.getDate("ad_expiry_date");
                java.sql.Date paymentDate = rs.getDate("payment_date");
                
                item.setItemId(itemId);
                
                
                UserDAO ud = new UserDAO();
                Seller seller = (Seller) ud.returnRecord(sellerId);
                item.setSeller(seller);
                

                CategoryDAO cd = new CategoryDAO();
                Category category = cd.returnRecord(categoryId);
                cd.closeResources();
                item.setCategory(category);  // categoryDAO takes care of this
                
                SubcategoryDAO scd = new SubcategoryDAO();
                Subcategory subcategory = scd.returnRecord(subcategoryId);
                scd.closeResources();
                item.setSubcategory(subcategory);  // subcategoryDAO takes care of this
                
                item.setPrice(price);
                item.setPriceFormat(priceFormat);
                item.setListedFor(listedFor);
                item.setDescription(description);
                ((Property) item).setLocation(location);
                item.setImage(image);
                item.setStatus(status);
                item.setPaymentMade(paymentMade);
                item.setAdExpiryDate(adExpiryDate);
                item.setPaymentDate(paymentDate);
                 
                properties.add(item);

            }

            return properties;
        
        }catch(SQLException | ClassNotFoundException e) {System.out.println(e); return null;}
		
	}


	@Override
	public int addRecordReturnKey(Item type) {
		
		int lastEnteredId = 0;
		
		if (type instanceof Property) {

			
			
			try {
				/**
				 *  Add to item table.
				 */
	            sql = "INSERT into item (seller_id, subcategory_id, listed_for, price, description, ad_expiry_date, location, status, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                      
	            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	            pst.setInt(1, type.getSeller().getSellerId());
	            pst.setInt(2, type.getSubcategory().getSubcategoryId()); // Select list with values 1,2,3,4
	            pst.setString(3, type.getListedFor()); // Select list with values 1,2,3,4 "Rent/Sale"
	            pst.setDouble(4, type.getPrice());
	            pst.setString(5, type.getDescription());
	            pst.setDate(6, type.getAdExpiryDate()); //Is current date
	            pst.setString(7, ((Property) type).getLocation());
	            pst.setString(8, "");
	            pst.setString(9, type.getImage());
	            
	            pst.executeUpdate();
	            rs=pst.getGeneratedKeys();
	            
	            if(rs.next()){
					lastEnteredId = rs.getInt(1);
				}

	            System.out.println("Property added to the database");
	             
	            

	        }catch(SQLException e) {System.out.println(e); return 0;}

		}
		
		return lastEnteredId;
		
	}

	/** METHOD TO CHANGE THE STATUS OF PROPERTY IN FOLLOWING CONDITIONS:
	 * 
	 * 1. SELLER AND BUYER AGREE TO DEAL AND ITEM IS FLAGGED AS SOLD
	 * 2. AD EXPIRES WHEN PACKAGE PERIOD ELAPSES AND SYSTEM FLAGS THE PROPERTY AS EXPIRED
	 * 3. WHEN SELLER PAYS FOR A PERIOD TO ADVERTISE AND THE ADMIN FLAGS THE PROPERTY ACTIVE
	 * 4. WHEN SELLER DECIDES THE ADVERTISEMENT SHOULD BE BLACKLISTED (ILLEGAL ACTIVITY)
	 * 
	 * @param itemId
	 * @param status
	 * @return
	 */
	public int updateStatus(int itemId, String status) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE item SET status = ? WHERE item_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, status);
	        pst.setInt(2, itemId);
	        

	        rowsAffected = pst.executeUpdate();
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
		
	}
	
	public int delistExpiredItemOnDay(java.sql.Date todaysDate) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE item SET status = ? WHERE ad_expiry_date <= ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, "expired");
	        pst.setDate(2, todaysDate);
	        

	        rowsAffected = pst.executeUpdate();
	        
	        System.out.println(rowsAffected+" items have been delisted");
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
		
	}
	
	
	public int updatePaymentMade(int itemId ,boolean b) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE item SET payment_made = ? WHERE item_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setBoolean(1, b);
	        pst.setInt(2, itemId);
	        

	        rowsAffected = pst.executeUpdate();
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
		
	}
	
	public int updatePaymentDate(int itemId ,java.sql.Date date) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE item SET payment_date = ? WHERE item_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setDate(1, date);
	        pst.setInt(2, itemId);
	        

	        rowsAffected = pst.executeUpdate();
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
		
	}
	
	/**
	 * Method used to change the expiry date of an item upon payment.
	 * @param itemId
	 * @param newExpiryDate
	 * @return
	 */
	public int updateExpiryDate(int itemId ,java.sql.Date newExpiryDate) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE item SET ad_expiry_date = ? WHERE item_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setDate(1, newExpiryDate);
	        pst.setInt(2, itemId);
	        

	        rowsAffected = pst.executeUpdate();
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
		
	}
	
	/**
	 *  This method returns the properties listed of certain type (res, com, ind, land etc.) and status active
	 *  
	 * @param type
	 * @param todaysDate
	 * @return
	 */
	public ArrayList<Item> returnActiveProperties(int type, java.sql.Date today) {
		
		Property item = null;
		ArrayList<Item> activeProperties = new ArrayList<>();
		
		try{
        	
            sql = "SELECT * FROM item WHERE subcategory_id = ? AND status = ? AND ad_expiry_date < ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, type);
            pst.setString(2, "active");
            pst.setDate(3, today);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	item = new Property();

            	//Retrieve info:
                int itemId = rs.getInt("item_id");
                int sellerId = rs.getInt("seller_id");
                int categoryId = rs.getInt("category_id");
                int subcategoryId = rs.getInt("subcategory_id");
                double price = rs.getDouble("price");
                String priceFormat = String.format("%.2f",price);
                String listedFor = rs.getString("listed_for");
                String location = rs.getString("location");
                String description = rs.getString("description");
                String status = rs.getString("status");
                String image = rs.getString("image");
                boolean paymentMade = rs.getBoolean("payment_made");
                java.sql.Date adExpiryDate = rs.getDate("ad_expiry_date");
                java.sql.Date paymentDate = rs.getDate("payment_date");
                
                item.setItemId(itemId);
                
                
                UserDAO ud = new UserDAO();
                Seller seller = (Seller) ud.returnRecord(sellerId);
                item.setSeller(seller);
                

                CategoryDAO cd = new CategoryDAO();
                Category category = cd.returnRecord(categoryId);
                cd.closeResources();
                item.setCategory(category);  // categoryDAO takes care of this
                
                SubcategoryDAO scd = new SubcategoryDAO();
                Subcategory subcategory = scd.returnRecord(subcategoryId);
                scd.closeResources();
                item.setSubcategory(subcategory);  // subcategoryDAO takes care of this
                
                item.setPrice(price);
                item.setPriceFormat(priceFormat);
                item.setListedFor(listedFor);
                item.setDescription(description);
                ((Property) item).setLocation(location);
                item.setImage(image);
                item.setStatus(status);
                item.setPaymentMade(paymentMade);
                item.setAdExpiryDate(adExpiryDate);
                item.setPaymentDate(paymentDate);

                
                activeProperties.add(item);

                
            }

            return activeProperties;
        
        }catch(SQLException | ClassNotFoundException e) {System.out.println(e); return null;}
		
		}
		
		public int getItemIndex(ArrayList<Item> properties, int itemId) { 
			
			for(int i = 0; i < properties.size(); i++) {
				
				if(properties.get(i).getItemId() ==  itemId) {
					return i;
				}
				
			}
			return -1;
		}

		public ArrayList<Item> returnSoldProperties() {

			
			Property item = null;
			ArrayList<Item> soldProperties = new ArrayList<>();
			
			try{
	        	
	            sql = "SELECT * FROM item WHERE status = ?";
	                
	            pst = conn.prepareStatement(sql);
	            
	            pst.setString(1, "sold");
	            
	            //Execute the query
	            rs = pst.executeQuery();
	            
	            //Extract from result set:
	            while(rs.next()) {

	            	item = new Property();

	            	//Retrieve info:
	                int itemId = rs.getInt("item_id");
	                int sellerId = rs.getInt("seller_id");
	                int categoryId = rs.getInt("category_id");
	                int subcategoryId = rs.getInt("subcategory_id");
	                double price = rs.getDouble("price");
	                String priceFormat = String.format("%.2f",price);
	                String listedFor = rs.getString("listed_for");
	                String location = rs.getString("location");
	                String description = rs.getString("description");
	                String status = rs.getString("status");
	                String image = rs.getString("image");
	                boolean paymentMade = rs.getBoolean("payment_made");
	                java.sql.Date adExpiryDate = rs.getDate("ad_expiry_date");
	                java.sql.Date paymentDate = rs.getDate("payment_date");
	                
	                item.setItemId(itemId);
	                
	                
	                UserDAO ud = new UserDAO();
	                Seller seller = (Seller) ud.returnRecord(sellerId);
	                item.setSeller(seller);
	                

	                CategoryDAO cd = new CategoryDAO();
	                Category category = cd.returnRecord(categoryId);
	                cd.closeResources();
	                item.setCategory(category);  // categoryDAO takes care of this
	                
	                SubcategoryDAO scd = new SubcategoryDAO();
	                Subcategory subcategory = scd.returnRecord(subcategoryId);
	                scd.closeResources();
	                item.setSubcategory(subcategory);  // subcategoryDAO takes care of this
	                
	                item.setPrice(price);
	                item.setPriceFormat(priceFormat);
	                item.setListedFor(listedFor);
	                item.setDescription(description);
	                ((Property) item).setLocation(location);
	                item.setImage(image);
	                item.setStatus(status);
	                item.setPaymentMade(paymentMade);
	                item.setAdExpiryDate(adExpiryDate);
	                item.setPaymentDate(paymentDate);

	                
	                soldProperties.add(item);

	                
	            }

	            return soldProperties;
	        
	        }catch(SQLException | ClassNotFoundException e) {System.out.println(e); return null;}

			
		}

		public ArrayList<Item> returnExpiredProperties() {
			
			Property item = null;
			ArrayList<Item> expiredProperties = new ArrayList<>();
			
			try{
	        	
	            sql = "SELECT * FROM item WHERE status = ?";
	                
	            pst = conn.prepareStatement(sql);
	            
	            pst.setString(1, "expired");
	            
	            //Execute the query
	            rs = pst.executeQuery();
	            
	            //Extract from result set:
	            while(rs.next()) {

	            	item = new Property();

	            	//Retrieve info:
	                int itemId = rs.getInt("item_id");
	                int sellerId = rs.getInt("seller_id");
	                int categoryId = rs.getInt("category_id");
	                int subcategoryId = rs.getInt("subcategory_id");
	                double price = rs.getDouble("price");
	                String priceFormat = String.format("%.2f",price);
	                String listedFor = rs.getString("listed_for");
	                String location = rs.getString("location");
	                String description = rs.getString("description");
	                String status = rs.getString("status");
	                String image = rs.getString("image");
	                boolean paymentMade = rs.getBoolean("payment_made");
	                java.sql.Date adExpiryDate = rs.getDate("ad_expiry_date");
	                java.sql.Date paymentDate = rs.getDate("payment_date");
	                
	                item.setItemId(itemId);
	                
	                
	                UserDAO ud = new UserDAO();
	                Seller seller = (Seller) ud.returnRecord(sellerId);
	                item.setSeller(seller);
	                

	                CategoryDAO cd = new CategoryDAO();
	                Category category = cd.returnRecord(categoryId);
	                cd.closeResources();
	                item.setCategory(category);  // categoryDAO takes care of this
	                
	                SubcategoryDAO scd = new SubcategoryDAO();
	                Subcategory subcategory = scd.returnRecord(subcategoryId);
	                scd.closeResources();
	                item.setSubcategory(subcategory);  // subcategoryDAO takes care of this
	                
	                item.setPrice(price);
	                item.setPriceFormat(priceFormat);
	                item.setListedFor(listedFor);
	                item.setDescription(description);
	                ((Property) item).setLocation(location);
	                item.setImage(image);
	                item.setStatus(status);
	                item.setPaymentMade(paymentMade);
	                item.setAdExpiryDate(adExpiryDate);
	                item.setPaymentDate(paymentDate);

	                
	                expiredProperties.add(item);

	                
	            }

	            return expiredProperties;
	        
	        }catch(SQLException | ClassNotFoundException e) {System.out.println(e); return null;}

			
		}
		
		
		

}
