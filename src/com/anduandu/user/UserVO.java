package com.anduandu.user;

import java.util.Date;

public class UserVO {

	private String userName;
	private String firstName;
	private String lastName;
	private String emailID;
	private String gender;
	private Date lastLoggedInTime;
	private LoggedInWith loggedInWith;
	
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
	public Date getLastLoggedInTime() {
		return lastLoggedInTime;
	}
	public void setLastLoggedInTime(Date lastLoggedInTime) {
		this.lastLoggedInTime = lastLoggedInTime;
	}
	public LoggedInWith getLoggedInWith() {
		return loggedInWith;
	}
	public void setLoggedInWith(LoggedInWith facebook) {
		this.loggedInWith = facebook;
	}
	
	
}
