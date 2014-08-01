package ua.bionic.adonin.messenger.data.entities;

import java.io.Serializable;

public class User implements Serializable {    
    
	private static final long serialVersionUID = 1L;
    private int userID;    
	private String username;
    private String password;
    private String email;
    private int type;
    private String firstname;
    private String lastname;
    private boolean activity;
	private String info;
    private String photo;  
    private boolean status;

	public User() {
    }
	
    public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean isActivity() {
		return activity;
	}

	public void setActivity(boolean activity) {
		this.activity = activity;
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
		User castedThat = (User) that;
		return (userID == castedThat.userID) &&
			(username.equals(castedThat.username)) &&
			(password.equals(castedThat.password)) &&
			(email.equals(castedThat.email)) &&
			(type == castedThat.type) &&
			(firstname.equals(castedThat.firstname)) &&
			(lastname.equals(castedThat.lastname)) &&
			(activity == castedThat.activity) &&
			(info.equals(castedThat.info)) &&
			(photo.equals(castedThat.photo)) &&
			(status == castedThat.status);
  	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + userID; 
		result = result * 31 + (username == null ? 0 : username.hashCode());
		result = result * 31 + (password == null ? 0 : password.hashCode());
		result = result * 31 + (email == null ? 0 : email.hashCode());
		result = result * 31 + type;
		result = result * 31 + (firstname == null ? 0 : firstname.hashCode());
		result = result * 31 + (lastname == null ? 0 : lastname.hashCode());
		result = result * 31 + (activity ? 1 : 0);
		result = result * 31 + (info == null ? 0 : info.hashCode());
		result = result * 31 + (photo == null ? 0 : photo.hashCode());
		result = result * 31 + (status ? 1 : 0);
		return result;
	}

}