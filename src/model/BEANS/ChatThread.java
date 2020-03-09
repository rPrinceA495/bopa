package model.BEANS;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ChatThread implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int threadId, accOne, accTwo;
	private java.sql.Timestamp timestamp;
	private ArrayList<ChatMessage> messages;
	
	public int getAccOne() {
		return accOne;
	}
	public void setAccOne(int accOne) {
		this.accOne = accOne;
	}
	public int getAccTwo() {
		return accTwo;
	}
	public void setAccTwo(int accTwo) {
		this.accTwo = accTwo;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public ArrayList<ChatMessage> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<ChatMessage> messages) {
		this.messages = messages;
	}
	
	public int getThreadId() {
		return threadId;
	}
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	

}
