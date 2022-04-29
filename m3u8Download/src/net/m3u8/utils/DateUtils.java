package com.soft.utils;

import org.joda.time.DateTime;

public class DateUtils {

	private static final String FORMAT = "yyyy-MM-dd";

	public static String getYesterday() {
		DateTime now = DateTime.now();
		DateTime yesterday = now.minusDays(1);
		return yesterday.toString(FORMAT);
	}
}
