package com.anduandu.builder;

import java.util.HashMap;
import java.util.Map;

import com.anduandu.user.LoggedInWith;

public class UserBuilderFactory {

	private static UserBuilderFactory userBuilderFactory = new UserBuilderFactory();
	private static Map<LoggedInWith, UserBuilder> userBuildeMap;
	
	public UserBuilder getUserBuilder(LoggedInWith loggedInWith) {
		setUserBuildeMap();
		return userBuildeMap.get(loggedInWith);
	}

	private void setUserBuildeMap() {
		if(userBuildeMap == null || userBuildeMap.size() == 0) {
			userBuildeMap = new HashMap<LoggedInWith, UserBuilder>();
			userBuildeMap.put(LoggedInWith.Facebook, new FacebookUserBuilder());
			userBuildeMap.put(LoggedInWith.GooglePlus, new GooglePlusUserBuilder());
		}
	}
	
	public static UserBuilderFactory getInstance() {
		return userBuilderFactory;
	}
	
}
