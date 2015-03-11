package com.anduandu.dao;

import com.anduandu.builder.UserBuilder;
import com.anduandu.builder.UserBuilderFactory;
import com.anduandu.user.LoggedInWith;
import com.anduandu.user.UserDetailsWrapper;
import com.anduandu.user.UserVO;

public class GooglePlusUserDataFetcher implements UserDataFetcher{

	private LoggedInWith loggedInWith = LoggedInWith.GooglePlus;

	@Override
	public UserVO fetchUserDetails(UserDetailsWrapper userDetailsWrapper) {
		UserBuilder userBuilder = UserBuilderFactory.getInstance().getUserBuilder(loggedInWith );
		UserVO userVO = userBuilder.buildUser(userDetailsWrapper);
		return userVO;
	}

}
