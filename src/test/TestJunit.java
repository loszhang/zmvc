package test;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.enterprise.inject.New;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;
import demo.utils.DateUtils;

public class TestJunit {
	
	/*@org.junit.Test
	public void test() {
		UUID uuid = new UUID(0,30);
		System.out.println(uuid.randomUUID().toString().length());
		System.out.println(UUID.randomUUID());
	}
	
	@Test
	public void joda_time() throws Exception {
		DateTime dateTime = new DateTime();
		LocalDate localDate = new LocalDate();
		LocalTime localTime =LocalTime.now();
		DateTime dateTime2 = new DateTime();
		Date date = new Date();
		System.out.println(date);
		System.out.println(new Time(new Date().getTime()));
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdFormat.format(date));;
	}*/
	
	@Test
	public void test2() {
		System.out.println(DateUtils.getTime());
	}
}
