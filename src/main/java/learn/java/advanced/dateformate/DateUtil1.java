package learn.java.advanced.dateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 我们都是优秀的程序员，我们都知道在程序中我们应当尽量少的创建SimpleDateFormat实例，因为创建这么一个实例需要耗费很大的代价。
 * 在一个读取数据库数据导出到excel文件的例子当中，每次处理一个时间信息的时候，就需要创建一个SimpleDateFormat实例对象，
 * 然后再丢弃这个对象。 大量的对象就这样被创建出来，占用大量的内存和 jvm空间。 代码如下：
 * 
 * @author lenovopc
 *
 */
public class DateUtil1 {

	public static Date parse(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateStr);
		return date;
	}

	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(date);
		return format;
	}

}
