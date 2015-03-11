package com.anduandu.builder;

import com.anduandu.user.UserDetailsWrapper;
import com.anduandu.user.UserVO;

public interface UserBuilder {

	public UserVO buildUser(UserDetailsWrapper userDetailsWrapper);
}
