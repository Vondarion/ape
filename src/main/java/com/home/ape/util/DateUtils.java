package com.home.ape.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class DateUtils.
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";

	/**
	 * Instantiates a new date utils.
	 */
	private DateUtils() {

	}

	public static String format(LocalDateTime dateTime) {
		if ( dateTime == null )
			return null;

		return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_FORMAT_PATTERN));

	}
}
