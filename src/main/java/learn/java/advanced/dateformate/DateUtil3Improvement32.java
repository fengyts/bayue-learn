package learn.java.advanced.dateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：使用ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。
 * 如果对性能要求比较高的情况下，一般推荐使用这种方法。
　　4.抛弃JDK，使用其他类库中的时间格式化类：
　　1.使用Apache commons 里的FastDateFormat，宣称是既快又线程安全的SimpleDateFormat, 可惜它只能对日期进行format, 不能对日期串进行解析。
　　2.使用Joda-Time类库来处理时间相关问题

 　　

　　做一个简单的压力测试，方法一最慢，方法三最快，但是就算是最慢的方法一性能也不差，一般系统方法一和方法二就可以满足，所以说在这个点很难成为你系统的瓶颈所在。
从简单的角度来说，建议使用方法一或者方法二，如果在必要的时候，追求那么一点性能提升的话，可以考虑用方法三，用ThreadLocal做缓存。
 * @author lenovopc
 *
 */
public class DateUtil3Improvement32 {

	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	public static Date parse(String dateStr) throws ParseException {
		SimpleDateFormat sdf = threadLocal.get();
		if (null == sdf) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		Date date = sdf.parse(dateStr);
		return date;
	}

	public static String format(Date date) {
		SimpleDateFormat sdf = threadLocal.get();
		if (null == sdf) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		String format = sdf.format(date);
		return format;
	}

}
