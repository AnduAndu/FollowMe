package com.anduandu.user;

public enum LoggedInWith {

	FollowMe("Follow Me"),
	GooglePlus("Google Plus"),
	Facebook("Facebook");
	
	private String loggedInWith;
	
	private LoggedInWith(String loggedInWith) {
		setLoggedInWith(loggedInWith);
	}
	public String getLoggedInWith() {
		return loggedInWith;
	}
	public void setLoggedInWith(String loggedInWith) {
		this.loggedInWith = loggedInWith;
	}
}
