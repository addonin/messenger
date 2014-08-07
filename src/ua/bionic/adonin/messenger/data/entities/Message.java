package ua.bionic.adonin.messenger.data.entities;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int messageID;
	private int senderID;
	private int receiverID;
	private String text;
	private boolean status;
	private Date timestamp;

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	
	public int getSenderID() {
		return senderID;
	}
	
 	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
 	
	public int getReceiverID() {
		return receiverID;
	}
	
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}		
		if (!(that instanceof Message)) {
			return false;
		}
		Message castedThat = (Message) that;
		return (senderID == castedThat.senderID) &&
			(receiverID == castedThat.receiverID) &&
			(text.equals(castedThat.text)) &&
			(status == castedThat.status) &&
			(timestamp.equals(castedThat.timestamp));
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + senderID; 
		result = result * 31 + receiverID;
		result = result * 31 + (text == null ? 0 : text.hashCode());
		result = result * 31 + (status ? 1 : 0);
		result = result * 31 + (timestamp == null ? 0 : timestamp.hashCode());
		return result;
	}
	
}
