package com.anduandu.builder;

import com.anduandu.user.LoggedInWith;
import com.anduandu.user.UserDetailsWrapper;
import com.anduandu.user.UserVO;
import com.anduandu.util.CalendarUtil;
import com.anduandu.util.UserUtil;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.Name;

public class GooglePlusUserBuilder implements UserBuilder{

	@Override
	public UserVO buildUser(UserDetailsWrapper userDetailsWrapper) {
		Person currentPerson = userDetailsWrapper.getPerson();
		UserVO userVO = new UserVO();
		Name name = currentPerson.getName();
		userVO.setFirstName(name.getGivenName());
		userVO.setLastName(name.getFamilyName());
		userVO.setUserName(currentPerson.getDisplayName());
		userVO.setGender(UserUtil.getGenderString(currentPerson.getGender()));
		userVO.setEmailID(Plus.AccountApi.getAccountName(userDetailsWrapper.getGoogleApiClient()));
		userVO.setLastLoggedInTime(CalendarUtil.getCurrentTime());
		userVO.setLoggedInWith(LoggedInWith.GooglePlus);
		return userVO;
	}

}
