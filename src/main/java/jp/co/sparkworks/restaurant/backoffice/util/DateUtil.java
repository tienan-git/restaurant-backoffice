package jp.co.sparkworks.restaurant.backoffice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	/**
	 * yyyy-MM-dd HH:mmにフォーマットする
	 * 
	 * @param datetime
	 *            LocalDateTime
	 * @return localDateTime
	 * 
	 */
	public static String localDateTimeFormat(LocalDateTime  datetime) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String localDateTime = df.format(datetime);
		
		return localDateTime;
	}

}
