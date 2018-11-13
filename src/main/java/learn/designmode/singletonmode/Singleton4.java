package learn.designmode.singletonmode;

/**
 * 单例模式：双检锁/双重校验锁（DCL，即 double-checked locking）
 * 
 * 注意区分<link>Singleton4Error</link>的区别
 * 
 * @author lenovopc
 *
 */
public class Singleton4 {

	private volatile static Singleton4 instance;

	private Singleton4() {
	}

	public static Singleton4 getInstance() {
		if (null == instance) {
			synchronized (Singleton4.class) {
				if (null == instance) {
					instance = new Singleton4();
				}
			}
		}
		return instance;
	}

}
