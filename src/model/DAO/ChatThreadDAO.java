package model.DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.BEANS.*;
public class ChatThreadDAO extends DAO<ChatThread> {

	public ChatThreadDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int checkThreadAvailability(int senderId, int receipientId) {
		
		int threadFound = 0;
		
		try{
            
            sql = "SELECT * FROM chat_thread WHERE (acc_one = ? AND acc_two = ?) OR acc_one = ? AND acc_two";
            
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, senderId);
            pst.setInt(2, receipientId);
 
            rs = pst.executeQuery();
            
            // Return thread_id or 0 depending on if thread exists or not.
            if(rs.next()) {
            	threadFound = rs.getInt("thread_id");
            }
            
            sql = "SELECT * FROM chat_thread WHERE acc_one = ? AND acc_two = ?";
            
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, receipientId);
            pst.setInt(2, senderId);
 
            rs = pst.executeQuery();
            
            // Return thread_id or 0 depending on if thread exists or not.
            if(rs.next()) {
            	threadFound = rs.getInt("thread_id");
            }
            
            return threadFound;
        
        }catch(SQLException e) {System.out.println(e); return threadFound;}
		
		
		
	}

	@Override
	public void addRecord(ChatThread type) {
		
		try {
			
            sql = "INSERT into chat_thread (acc_one, acc_two, stamp) VALUES (?, ?, ?)";
                      
            pst = this.conn.prepareStatement(sql);
            
            pst.setInt(1, type.getAccOne()); 
            pst.setInt(2, type.getAccTwo()); 
            pst.setTimestamp(3, type.getTimestamp());
            
            pst.executeUpdate();
            
            System.out.println("Thread added to the database");
            

        }catch(SQLException e) {System.out.println(e); }
		
	}


	@Override
	public int updateRecord(ChatThread type) {
		// TODO Auto-generated method stub
		
		return 0;
	}




	@Override
	public ChatThread returnRecord(int key) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public ArrayList<ChatThread> returnRecords(int key) {

		
			ArrayList<ChatThread> threads = new ArrayList<>();
		
			try{
				
				ChatMessageDAO cmd = new ChatMessageDAO();
	        	
	            sql = "SELECT * FROM chat_thread WHERE acc_one = ? or acc_two = ?";
	                
	            pst = conn.prepareStatement(sql);
	            
	            pst.setInt(1, key);
	            pst.setInt(2, key);
	            
	            rs = pst.executeQuery();

	            while(rs.next()) {
	
	                int threadId = rs.getInt("thread_id");
	                int accOne = rs.getInt("acc_one");
	                int accTwo = rs.getInt("acc_two");
	                java.sql.Timestamp timestamp = rs.getTimestamp("stamp");
	                
	                ChatThread ct = new ChatThread();
	                
	                ct.setThreadId(threadId);
	                ct.setAccOne(accOne);
	                ct.setAccTwo(accTwo);
	                ct.setTimestamp(timestamp);
	                
	                threads.add(ct);
	            }
	
	            // Populate all threads with their messages:
	            for(ChatThread thread: threads) {
	            	
	            	ArrayList<ChatMessage> messages = new ArrayList<>();
	            	messages = cmd.returnRecords(thread.getThreadId());
	            	thread.setMessages(messages);
	            	
	            }
	            
	            return threads;
	        
	        }catch(SQLException e) {
	        	
	        	System.out.println(e); return null;
	        
	        } catch (ClassNotFoundException e) {
				
				e.printStackTrace(); return null;
			}
		
	}
	


	@Override
	public int deleteRecord(int key) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "DELETE FROM chat_thread WHERE thread_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, key);
	        

			rowsAffected = pst.executeUpdate();

        
        }catch(SQLException e) {System.out.println(e);}
		
		
		return rowsAffected;	
		
	}

	@Override
	public int addRecordReturnKey(ChatThread type) {
		
		int lastEnteredId = 0;
		
		try {
			
            sql = "INSERT into chat_thread (acc_one, acc_two, stamp) VALUES (?, ?, ?)";
                      
            pst = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, type.getAccOne()); 
            pst.setInt(2, type.getAccTwo()); 
            pst.setTimestamp(3, type.getTimestamp());
            
            pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			
			if(rs.next()) {
				lastEnteredId = rs.getInt(1); // index 1 is the "id" column
			}
            
            System.out.println("Thread added to the database");
            
            return lastEnteredId;
            

        }catch(SQLException e) {System.out.println(e); return lastEnteredId;}
		
	}

}
