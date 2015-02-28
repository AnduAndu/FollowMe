package com.anduandu.user;

public class UserVO {

	private String userName;
	private String firstName;
	private String lastName;
	private String emailID;
	private String gender;
	private long lastLoggedInTime;
	private String loggedInWith;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getLastLoggedInTime() {
		return lastLoggedInTime;
	}
	public void setLastLoggedInTime(long lastLoggedInTime) {
		this.lastLoggedInTime = lastLoggedInTime;
	}
	public String getLoggedInWith() {
		return loggedInWith;
	}
	public void setLoggedInWith(String loggedInWith) {
		this.loggedInWith = loggedInWith;
	}
	
	
}
