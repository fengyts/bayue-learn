package learn.java.advanced.dateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtil改进，高并发下效率低
 * @author lenovopc
 *
 */
public class DateUtil3Improvement2 {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date parse(String dateStr) throws ParseException {
		synchronized (sdf) {
			Date date = sdf.parse(dateStr);
			return date;
		}
	}

	public static String format(Date date) {
		synchronized (sdf) {
			String format = sdf.format(date);
			return format;
		}
	}

}
