package com.anduandu.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserUtil {

	private static Map<Integer, String> genderMap;
	
	public static String getGenderString(int gender) {
		genderMap = getGemderMap();
		return genderMap.get(gender);
	}
	private static Map<Integer, String> getGemderMap() {

		genderMap = new HashMap<Integer, String>();
		genderMap.put(0, "Male");
		genderMap.put(1, "Female");
		genderMap.put(2, "Other");
		
		return Collections.unmodifiableMap(genderMap);
	}
}
