package com.anduandu.dao;

import com.anduandu.user.UserDetailsWrapper;
import com.anduandu.user.UserVO;

public interface UserDataFetcher {

	public UserVO fetchUserDetails(UserDetailsWrapper userDetailsWrapper);
	
}
