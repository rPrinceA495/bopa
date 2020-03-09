package model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*; 
import java.util.ArrayList;
import java.util.Set;

import model.BEANS.Admin;
import model.BEANS.User;



public abstract class DAO <T> {
	
	
	/** 
	 * Static constants:*********************************************************************
	 */
	
	//Database and driver info:
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/bopa_db";
    
    //Database user credentials:
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root123";
    
    /** 
	 * ***************************************************************************************
	 */
    
	/** 
	 * Inherited fields:**********************************************************************
	 */
	
    protected Connection conn;
    protected PreparedStatement pst;
    protected Statement st;
    protected String sql; 
    protected ResultSet rs;
    
    
    /** 
	 * ***************************************************************************************
	 */
    
    
 	//private T type; // Generic type:

    
    /**
     *  Constructor for DAO object (not directly called)
     *  
     * @throws ClassNotFoundException
     */
    public DAO() throws ClassNotFoundException {
        
        try{
        	
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //Open connection
            this.conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //System.out.println("Successfully connected.");
            
            //Initialize other fields to null;
            this.pst = null;
            this.st = null;
            this.sql = null; 
            this.rs = null;
            
        
        }catch(SQLException e) {
        	System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        
    }
    
    public abstract void addRecord(T type);
    
    public abstract int addRecordReturnKey(T type);
    
    public abstract int updateRecord(T type);  
    
    public abstract T returnRecord(int key);
    
    public abstract ArrayList<T> returnRecords(int key);
    
    public abstract int deleteRecord(int key);
    
	
    /**
     * CLEAN UP ENVIRONMENT:
     */
    public void closeResources() {
    	
    	try{
            //Close Statement object if it is still open:
            if(pst != null)
                pst.close();
        
        }catch(SQLException se2) {System.out.println(se2);}
        
        try{
            //Close Statement object if it is still open:
            if(st != null)
                st.close();
        
        }catch(SQLException se2) {System.out.println(se2);}
        
        try{
            //Close Connection object if it is still open:
            if(conn != null)
                conn.close();
        
        }catch(SQLException e) {System.out.println(e);}
        
        try{
            //Close Statement object if it is still open:
            if(rs != null)
                rs.close();
        
        }catch(SQLException se2) {System.out.println(se2);}
    }

}
