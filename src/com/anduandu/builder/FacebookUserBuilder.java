package com.anduandu.builder;

import com.anduandu.user.LoggedInWith;
import com.anduandu.user.UserDetailsWrapper;
import com.anduandu.user.UserVO;
import com.anduandu.util.CalendarUtil;
import com.facebook.model.GraphUser;

public class FacebookUserBuilder implements UserBuilder{

	@Override
	public UserVO buildUser(UserDetailsWrapper userDetailsWrapper) {
		GraphUser user = userDetailsWrapper.getGraphUser();
		UserVO userVO = new UserVO();
		userVO.setUserName(user.getName());
		userVO.setFirstName(user.getFirstName());
		userVO.setLastName(user.getLastName());
		userVO.setEmailID(user.getProperty("email").toString());
		userVO.setGender(user.getProperty("gender").toString());
		userVO.setLastLoggedInTime(CalendarUtil.getCurrentTime());
		userVO.setLoggedInWith(LoggedInWith.Facebook);
		return userVO;
	}

}
