package learn.java.advanced.dateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 你也许会说，OK，那我就创建一个静态的simpleDateFormat实例，然后放到一个DateUtil类（如下）中，在使用时直接使用这个实例进行操作，
 * 这样问题就解决了。
 * 
 * 当然，这个方法的确很不错，在大部分的时间里面都会工作得很好。但当你在生产环境中使用一段时间之后，你就会发现这么一个事实：它不是线程安全的。
 * 在正常的测试情况之下，都没有问题，但一旦在生产环境中一定负载情况下时，这个问题就出来了。
 * 他会出现各种不同的情况，比如转化的时间不正确，比如报错，比如线程被挂死等等。 我们看下面的测试用例，那事实说话：
 * 
 * @author lenovopc
 *
 */
public class DateUtil2 {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date parse(String dateStr) throws ParseException {
		Date date = sdf.parse(dateStr);
		return date;
	}

	public static String format(Date date) {
		String format = sdf.format(date);
		return format;
	}

}
