package ua.bionic.adonin.messenger.data.entities;

import java.io.Serializable;

public class Relation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int fromID;
	private int toID;
	
	public int getFromID() {
		return fromID;
	}
	public void setFromID(int fromID) {
		this.fromID = fromID;
	}
	public int getToID() {
		return toID;
	}
	public void setToID(int toID) {
		this.toID = toID;
	}
	
	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}
		if (this == that) {
			return true;
		}
		if (!(that instanceof Message)) {
			return false;
		}
		Relation castedThat = (Relation) that;
		return (fromID == castedThat.fromID) &&
			(toID == castedThat.toID);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + fromID; 
		result = result * 31 + toID;
		return result;
	}
		
}
