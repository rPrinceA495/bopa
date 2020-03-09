package model.BEANS;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChatMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// FIRST OF ALL, QUERY DATABASE FOR ALL THREADS THAT USER PARTICIPATES IN AND IST IN TABULAR FORMAT. WHEN CLICKED, GET THE THREAD ID AND THEN QUERY CHAT_MSG TO GET ALL CHATS ASSOCIATED TO THAT THREAD.
	
	private int msgId, threadId ,senderId, receipientId;
	private String msgContent;
	private java.sql.Timestamp timestamp;
	private boolean read;
	
	
	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public int getReceipientId() {
		return receipientId;
	}

	public void setReceipientId(int receipientId) {
		this.receipientId = receipientId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	
	
	

	

}
