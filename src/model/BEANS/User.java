package model.BEANS;

import javax.servlet.http.HttpSession;

import model.DAO.ChatMessageDAO;
import model.DAO.ChatThreadDAO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

abstract public class User implements Serializable{
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected int accId;
	protected String firstName, lastName, oomangNo, postalAddress, emailAddress, password, profilePic, userType;
	protected Date dob;
	protected char gender;
	protected byte[] salt;
	protected ArrayList<ChatThread> threads;
	
	
	/**
	 * Constructor with no arguments:
	 */
	public User() {}

	public User(int accId, String firstName, String lastName, String oomangNo, String postalAddress,
			String emailAddress, String password, String profilePic, String userType, Date dob, char gender,
			byte[] salt) {
		super();
		this.accId = accId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.oomangNo = oomangNo;
		this.postalAddress = postalAddress;
		this.emailAddress = emailAddress;
		this.password = password;
		this.profilePic = profilePic;
		this.userType = userType;
		this.dob = dob;
		this.gender = gender;
		this.salt = salt;
	}




	public byte[] getSalt() {
		return salt;
	}




	public void setSalt(byte[] salt) {
		this.salt = salt;
	}




	public ArrayList<ChatThread> getThreads() {
		return threads;
	}




	public void setThreads(ArrayList<ChatThread> threads) {
		this.threads = threads;
	}


	/**
	 * *****************************************************GETTER AND SETTER METHODS**********************************************************
	 */

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String getOomangNo() {
		return oomangNo;
	}

	public void setOomangNo(String oomangNo) {
		this.oomangNo = oomangNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	
	
	/**
	 * *****************************************************BUSINESS LOGIC**********************************************************
	 */
	
	/**
	 * 
	 * @param accId is the accountId for the item seller or admin
	 * @param msgContent is the message tyoed
	 * @return
	 */
	public boolean sendMessage(int senderId, int receipientId, String msgContent) {
		
		try {
			
			ChatThreadDAO ctd = new ChatThreadDAO();
			ChatMessageDAO cmd = new ChatMessageDAO();
			
			int threadId = ctd.checkThreadAvailability(senderId, receipientId);
			
			// Check if it already has a thread else create one
			if(threadId == 0) {
				
				ChatThread ct = new ChatThread();
				ct.setAccOne(senderId);
				ct.setAccTwo(receipientId);
				
				//Code to get a current timestamp to allow sorting of  threads.
				long threadTime = System.currentTimeMillis();
				java.sql.Timestamp threadStamp = new Timestamp(threadTime);
				ct.setTimestamp(threadStamp);
				
				threadId = ctd.addRecordReturnKey(ct);
				
				ctd.closeResources();
				
				//sender.getThreads().add(ct);
				
				// Add message to the new thread (last one created)
				ChatMessage cm = new ChatMessage();
				cm.setThreadId(threadId); // Update with last entered gen keys
				cm.setSenderId(senderId);
				cm.setReceipientId(receipientId);
				
				//Code to get a current timestamp to allow sorting of messages.
				long msgTime = System.currentTimeMillis();
				java.sql.Timestamp msgStamp = new Timestamp(msgTime);
				cm.setTimestamp(msgStamp);
				
				cm.setMsgContent(msgContent);
				cmd.addRecord(cm);
				
				cmd.closeResources();
				
				return true;
				
				//sender.getThreads().get(-1).getMessages();
				
			}else { // Update existing thread by adding chat_messages to it:
				
				// Add message to the new thread (last one created)
				ChatMessage cm = new ChatMessage();
				cm.setThreadId(threadId); 
				cm.setSenderId(senderId);
				cm.setReceipientId(receipientId);
				
				//Code to get a current timestamp to allow sorting of messages.
				long msgTime = System.currentTimeMillis();
				java.sql.Timestamp msgStamp = new Timestamp(msgTime);
				cm.setTimestamp(msgStamp);
				
				cm.setMsgContent(msgContent);
				cmd.addRecord(cm);
				
				return true;
				
			}

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); return false;
		}
		
		
	}
	
	public void logout(HttpSession session) {
		
		session.invalidate();
		
	}

		
	
	
	
	
	
	
	
	
	

}


