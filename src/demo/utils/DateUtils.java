package demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String getDate() {
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sdDateFormat.format(new Date());
	}
	
	public static String getTime() {
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("hh:mm:ss");
		return sdDateFormat.format(new Date());
	}
	public static Date getTimeByDate() {
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("hh:mm:ss");
		
		;
		Date timeDate = new Date(sdDateFormat.format(new Date()));
		return timeDate;
	}

}
