package com.anduandu.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
}
