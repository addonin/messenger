package ua.bionic.adonin.messenger.data.entities;

import java.io.Serializable;

public class UserType implements Serializable{

	private static final long serialVersionUID = 1L;	
    private int userTypeID;
	private String userType;	
	
	public int getUserTypeID() {
		return userTypeID;
	}
	public void setId(int userTypeID) {
		this.userTypeID = userTypeID;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
		UserType castedThat = (UserType) that;
		return (userTypeID == castedThat.userTypeID) && (userType.equals(castedThat.userType));
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + userTypeID; 
		result = result * 31 + (userType == null ? 0 : userType.hashCode());
		return result;
	}
}
