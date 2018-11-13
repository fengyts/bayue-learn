package learn.designmode.singletonmode;

/**
 * 单例模式：登记式/静态内部类
 * 
 * @author lenovopc
 *
 */
public class Singleton5 {

	private static final class SingleInstance {
		private static final Singleton5 instance = new Singleton5();
	}

	private Singleton5() {
	}

	public static Singleton5 getInstance() {
		return SingleInstance.instance;
	}

}
