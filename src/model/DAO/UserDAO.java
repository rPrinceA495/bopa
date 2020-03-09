package model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*; 
import java.util.ArrayList;
import java.util.Set;

import model.BEANS.*;

public class UserDAO extends DAO<User> {
    
	public UserDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public int validateUser(String email, String pass) {
		
		// Defaulted to zero in case it doesn't exist
		int accId = 0;
        
		//execute query that checks agent, customer and owner tables for a match with passed in parameters:
        try{
            
            sql = "SELECT * from user_account WHERE email_address = ? AND password = ?";
            
            
            pst = conn.prepareStatement(sql);
            
            //Assign values to question marks:
            pst.setString(1, email);
            pst.setString(2, pass);
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            if(rs.next()) {
            	accId = rs.getInt("acc_id");
            	return accId;
            }
            
            else {
            	return accId;
            }

        }catch(SQLException e) {System.out.println(e); return accId;}
        
    }

	@Override
	public void addRecord(User type) {
		
		if (type instanceof Admin) {
			
			try{
				
				/**
				 *  Add to admin table.
				 */
	            sql = "INSERT into admin (f_name, l_name, gender, oomang_no, dob, postal_address, profile_pic) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                      
	            pst = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	            
	            pst.setString(1, type.getFirstName());
	            pst.setString(2, type.getLastName());
	            pst.setString(3, Character.toString(type.getGender()));
	            pst.setString(4, type.getOomangNo());
	            pst.setDate(5, type.getDob());
	            pst.setString(6, type.getPostalAddress());
	            pst.setString(7, "../uploads/profile_pics/profile.png");
	            
	            
	            pst.executeUpdate();
				rs=pst.getGeneratedKeys();
				
				int lastEnteredId = 0;
				
				if(rs.next()){
					lastEnteredId = rs.getInt(1);
				}
	            
	            System.out.println("Admin added to the database");
	            
	            /**
				 *  Add to user_account.
				 */
	            sql = "INSERT into user_account (email_address, password, admin_id, user_type, salt) VALUES (?, ?, ?, ?, ?)";
                
	            
	            pst = this.conn.prepareStatement(sql);
	            
	            pst.setString(1, type.getEmailAddress());
	            pst.setString(2, type.getPassword());
	            pst.setInt(3, lastEnteredId); //Get last entered ID! VALUE IS PRIMARY KEY IN FORM{TALKING OF CATEGORIES ETC.}!
	            pst.setString(4, "Admin");
	            pst.setBytes(5, type.getSalt());
	            
	            // Execute update:
	            pst.executeUpdate();
	            
	            System.out.println("Admin account added.");

	        }catch(SQLException e) {System.out.println(e); }
			
		}else if (type instanceof Seller) {
			
			try{
				/**
				 *  Add to seller table.
				 */
	            sql = "INSERT into seller (f_name, l_name, gender, oomang_no, dob, postal_address ,contact_no, profile_pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	                      
	            pst = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	            pst.setString(1, type.getFirstName());
	            pst.setString(2, type.getLastName());
	            pst.setString(3, Character.toString(type.getGender()));
	            pst.setString(4, type.getOomangNo());
	            pst.setDate(5, type.getDob());
	            pst.setString(6, type.getPostalAddress());
	            pst.setInt(7, ((Seller) type).getContactNo());
	            pst.setString(8, "../uploads/profile_pics/profile.png");
	            
	            // Execute update:
	            pst.executeUpdate();
	            rs=pst.getGeneratedKeys();
				
				int lastEnteredId = 0;
				
				if(rs.next()){
					lastEnteredId = rs.getInt(1);
				}
	            
	            System.out.println("Seller added to the database");
	            
	            /**
				 *  Add to user_account table.
				 */
	            sql = "INSERT into user_account (email_address, password, seller_id, user_type, salt) VALUES (?, ?, ?, ?, ?)";
                
	            
	            pst = this.conn.prepareStatement(sql);
	            
	            
	            pst.setString(1, type.getEmailAddress());
	            pst.setString(2, type.getPassword());
	            pst.setInt(3, lastEnteredId); // Last entered ID
	            pst.setString(4, "Seller");
	            pst.setBytes(5, type.getSalt());
	            
	            pst.executeUpdate();
	             
	            System.out.println("Seller account added.");

	        }catch(SQLException e) {System.out.println(e); }
			
		}else {
			return;
		}
		
	}
	
	public int checkEmail(String email) {
	    
        
        try{
            
            sql = "SELECT * FROM user_account WHERE email_address = ?";
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, email);
 
            rs = pst.executeQuery();
            
            // Return 1 or 0 depending on if email already exists or not.
            if(rs.next()) {return 1;}
            
            else {return 0;}
        
        }catch(SQLException e) {System.out.println(e); return 0;}
        
    }
	
	public byte[] getPasswordSalt(String emailAddress) {
		
		try{
			
			byte[] salt = new byte[16];
            
            sql = "SELECT salt FROM user_account WHERE email_address = ?";
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, emailAddress);
 
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {
            	salt = rs.getBytes("salt");
            }
            return salt;
        
        }catch(SQLException e) {System.out.println(e); return null;}
 
	}
	
	
	/**
	 * 
	 * @param key is account ID that entered valid credentials. 
	 * @return is of type User object which will be passed into function to get remaining Admin or Seller info depending on instance.
	 */
	public User getUser(int key) {
		
        try{
        	
            sql = "SELECT * FROM user_account WHERE acc_id = ?";
            
            
            pst = conn.prepareStatement(sql);
            
            //Assign values to question marks:
            pst.setInt(1, key);
            
            //Execute the query
            rs = pst.executeQuery();
            
            User user = null;
            
            //Extract from result set:
            while(rs.next()) {
            
            	//Retrieve account info:
            	int accId = rs.getInt("acc_id");//1
            	
            	String userType = rs.getString("user_type");
            	
            	if(userType.equals("Admin")) {
            		
            		int adminId = rs.getInt("admin_id");
            		String emailAddress = rs.getString("email_address");
                    String password = rs.getString("password");
                    byte[] salt = rs.getBytes("salt");
                    
                    user = new Admin();
                    user.setAccId(accId);
                    user.setEmailAddress(emailAddress);
                    user.setPassword(password);
                    ((Admin) user).setAdminId(adminId);
                    user.setUserType(userType);
                    user.setSalt(salt);
                    
                    
            	}else if(userType.equals("Seller")){
            		
            		
            		int sellerId = rs.getInt("seller_id");
            		String emailAddress = rs.getString("email_address");
                    String password = rs.getString("password");
                    byte[] salt = rs.getBytes("salt");
                    
                    user = new Seller();
                    user.setAccId(accId);
                    user.setEmailAddress(emailAddress);
                    user.setPassword(password);
                    ((Seller) user).setSellerId(sellerId);
                    user.setUserType(userType);
                    user.setSalt(salt);
            	}
                
            }
            
            return user;
                    
        }catch(SQLException e) {System.out.println(e); return null;}
         
	}
	
	public Admin getAdmin(Admin admin) {
		
        try{
        	
            sql = "SELECT * FROM admin WHERE admin_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, admin.getAdminId());
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	//Retrieve info:
                String firstName = rs.getString("f_name");
                String lastName = rs.getString("l_name");
                String gender = rs.getString("gender");
                String oomangNo = rs.getString("oomang_no");
                //java.sql.Date dob = Date.valueOf(rs.getString("dob"));
                java.sql.Date dob = rs.getDate("dob");
                String postalAddress = rs.getString("postal_address");
                String profilePic = rs.getString("profile_pic");
                
                admin.setFirstName(firstName);
                admin.setLastName(lastName);
                admin.setGender(gender.charAt(0));
                admin.setOomangNo(oomangNo);
                admin.setDob(dob);
                admin.setPostalAddress(postalAddress);
                admin.setProfilePic(profilePic);
                
            }

            return admin;
        
        }catch(SQLException e) {System.out.println(e); return null;}
         
	}
	
	
	public Seller getSeller(Seller seller) {
		
        try{
        	
            sql = "SELECT * FROM seller WHERE seller_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, seller.getSellerId());
            
            //Execute the query
            rs = pst.executeQuery();
            
            //Extract from result set:
            while(rs.next()) {

            	//Retrieve info:
                String firstName = rs.getString("f_name");
                String lastName = rs.getString("l_name");
                String gender = rs.getString("gender");
                String oomangNo = rs.getString("oomang_no");
                int contactNo = rs.getInt("contact_no");
                //java.sql.Date dob = Date.valueOf(rs.getString("dob"));
                java.sql.Date dob = rs.getDate("dob");
                String postalAddress = rs.getString("postal_address");
                String profilePic = rs.getString("profile_pic");
                
                seller.setFirstName(firstName);
                seller.setLastName(lastName);
                seller.setGender(gender.charAt(0));
                seller.setOomangNo(oomangNo);
                seller.setContactNo(contactNo);
                seller.setDob(dob);
                seller.setPostalAddress(postalAddress);
                seller.setProfilePic(profilePic);
                
            }

            return seller;
        
        }catch(SQLException e) {System.out.println(e); return null;}
         
	}
	
	public int changePassword(int accId, String newPassword, byte[] newSalt) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "UPDATE user_account SET password = ?, salt = ? WHERE acc_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, newPassword);
			pst.setBytes(2, newSalt);
	        pst.setInt(3, accId);
	        
	        rowsAffected = pst.executeUpdate();
	        
	        System.out.println("Password successfully changed");
        
        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
		
		return rowsAffected;
	}
	
	

	@Override
	public int updateRecord(User type) {
		
		int rowsAffected = 0;
		
		
		if (type instanceof Admin) {
			
			try{
	        	
				sql = "UPDATE admin SET f_name = ?, l_name = ?, gender = ?, oomang_no = ?, dob = ?, postal_address = ?, profile_pic = ? WHERE admin_id = ?";
				
				pst = conn.prepareStatement(sql);
				
				pst.setString(1, type.getFirstName());
		        pst.setString(2, type.getLastName());
		        pst.setString(3, Character.toString(type.getGender()));
		        pst.setString(4, type.getOomangNo());
		        pst.setDate(5, type.getDob());
		        pst.setString(6, type.getPostalAddress());
		        pst.setString(7, type.getProfilePic());
		        pst.setInt(8, ((Admin) type).getAdminId());

		        rowsAffected = pst.executeUpdate();
	        
	        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	

			
		}else if (type instanceof Seller) {
			
			try{
	        	
				sql = "UPDATE seller SET f_name = ?, l_name = ?, gender = ?, oomang_no = ?, contact_no = ?, dob = ?, postal_address = ?, profile_pic = ? WHERE seller_id = ?";	
				
				pst = conn.prepareStatement(sql);
				
				pst.setString(1, type.getFirstName());
		        pst.setString(2, type.getLastName());
		        pst.setString(3, Character.toString(type.getGender()));
		        pst.setString(4, type.getOomangNo());
		        pst.setInt(5, ((Seller) type).getContactNo());
		        pst.setDate(6, type.getDob());
		        pst.setString(7, type.getPostalAddress());
		        pst.setString(8, type.getProfilePic());
		        pst.setInt(9, ((Seller) type).getSellerId());

		        rowsAffected = pst.executeUpdate();
	        
	        }catch(SQLException e) {System.out.println(e); return rowsAffected;}	
			
		}
		
		return rowsAffected;
	}
	
	
	

	@Override
	public User returnRecord(int key) {
		
		User user = null;
		
		try {
			
			sql = "SELECT * from seller WHERE seller_id = ?";
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, key);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				int sellerId = rs.getInt("seller_id");
				String firstName = rs.getString("f_name");
				String lastName = rs.getString("l_name");
				
				int contactNo = rs.getInt("contact_no");
				String profilePic = rs.getString("profile_pic");
				
				user = new Seller();
				
				((Seller) user).setSellerId(sellerId);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				((Seller) user).setContactNo(contactNo);
				user.setProfilePic(profilePic);
				
				sql = "SELECT acc_id from user_account WHERE seller_id = ?";
				
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, key);
				
				rs = pst.executeQuery();
				
				if(rs.next()) {
					
					int accId = rs.getInt("acc_id");
					
					user.setAccId(accId);
				}
				
			}
			
			return user;
			

		}catch(SQLException e) {
			{System.out.println(e); return null;}
		}
		
	}

	@Override
	public int deleteRecord(int key) {return 0;}


	@Override
	public ArrayList<User> returnRecords(int key) {
		
		return null;
	}


	@Override
	public int addRecordReturnKey(User type) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
    

}
