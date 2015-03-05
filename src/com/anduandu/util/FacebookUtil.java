package com.anduandu.util;

import java.util.ArrayList;
import java.util.List;

public class FacebookUtil {

	public static List<String> getPermissions() {
		ArrayList<String> permissions = new ArrayList<String>();
		permissions.add("email");
		permissions.add("public_profile");
		permissions.add("user_friends");
		return permissions;
	}
	
}
