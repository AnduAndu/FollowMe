package com.anduandu.dao;

import java.util.HashMap;
import java.util.Map;

import com.anduandu.user.LoggedInWith;

public class UserDataFetcherFactory {

	private static UserDataFetcherFactory userDataFetcherFactory = new UserDataFetcherFactory();
	
	private static Map<LoggedInWith, UserDataFetcher> userDataFetcherMap;
	
	public UserDataFetcher getUserDataFetcher(LoggedInWith loggedInWith) {
		setUserDataFetcherMap();
		return userDataFetcherMap.get(loggedInWith);
	}

	private void setUserDataFetcherMap() {

		if(userDataFetcherMap == null || userDataFetcherMap.size() == 0) {
			userDataFetcherMap = new HashMap<LoggedInWith, UserDataFetcher>();
			userDataFetcherMap.put(LoggedInWith.Facebook, new FacebookUserDataFetcher());
			userDataFetcherMap.put(LoggedInWith.GooglePlus, new GooglePlusUserDataFetcher());
		}
	}
	
	public static UserDataFetcherFactory getInstance() {
		return userDataFetcherFactory; 
	}
}
