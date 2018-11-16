package learn.java.advanced.dateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil3Improvement31 {

	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public static Date parse(String dateStr) throws ParseException {
		Date date = threadLocal.get().parse(dateStr);
		return date;
	}

	public static String format(Date date) {
		String format = threadLocal.get().format(date);
		return format;
	}

}
