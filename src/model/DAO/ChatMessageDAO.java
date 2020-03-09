package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import model.BEANS.ChatMessage;
import model.BEANS.ChatThread;

public class ChatMessageDAO extends DAO<ChatMessage> {

	public ChatMessageDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addRecord(ChatMessage type) {
		
		try {
			
            sql = "INSERT into chat_thread (thread_id, sender_id, receipient_id, msg_content, stamp) VALUES (?, ?, ?, ?, ?)";
                      
            pst = this.conn.prepareStatement(sql);
            
            pst.setInt(1, type.getThreadId());
            pst.setInt(2, type.getSenderId()); 
            pst.setInt(3, type.getReceipientId()); 
            pst.setString(4, type.getMsgContent());
            pst.setTimestamp(5, type.getTimestamp());
            
            pst.executeUpdate();
            
            System.out.println("Message added to the database.");
            

        }catch(SQLException e) {System.out.println(e); }
		
		
	}

	@Override
	public int updateRecord(ChatMessage type) {
		// TODO Auto-generated method stub
		
		return 0;
		
	}

	@Override
	public ChatMessage returnRecord(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ChatMessage> returnRecords(int key) {
		
		ArrayList<ChatMessage> messages = new ArrayList<>();
		
		try{
        	
            sql = "SELECT * FROM chat_messages WHERE thread_id = ?";
                
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, key);
            
            rs = pst.executeQuery();

            while(rs.next()) {

            	int msgId = rs.getInt("msg_id");
            	int threadId = rs.getInt("thread_id");
            	int senderId = rs.getInt("sender_id");
            	int receipientId = rs.getInt("receipient_id");
                String msgContent = rs.getString("acc_two");
                java.sql.Timestamp timestamp = rs.getTimestamp("stamp");
                boolean read = rs.getBoolean("read");
                
                ChatMessage cm = new ChatMessage();
                
                cm.setMsgId(msgId);
                cm.setThreadId(threadId);
                cm.setSenderId(senderId);
                cm.setReceipientId(receipientId);
                cm.setMsgContent(msgContent);
                cm.setTimestamp(timestamp);
                cm.setRead(read);
                
                messages.add(cm);
            }

            return messages;
        
        }catch(SQLException e) {System.out.println(e); return null;}
		
	}

	@Override
	public int deleteRecord(int key) {
		
		int rowsAffected = 0;
		
		try{
        	
			sql = "DELETE FROM chat_message WHERE msg_id = ?";	
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, key);
	        

			rowsAffected = pst.executeUpdate();

        
        }catch(SQLException e) {System.out.println(e);}
		
		
		return rowsAffected;	
		
	}

	@Override
	public int addRecordReturnKey(ChatMessage type) {
		
		return 0;
	}

	

	
	
	
	

}
