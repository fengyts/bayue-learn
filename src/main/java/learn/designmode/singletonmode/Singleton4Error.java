package learn.designmode.singletonmode;

/**
 * <link>Singleton4</link>
 * 注意这种方式写法是线程不安全的，是错误写法; 区别: 1.没有使用volatile 2. synchronized(instance)
 * 锁对象不是Singleton4Error.class
 * 
 * 解释： 这样写会导致线程问题了，比如A线程进入synchronized代码块了，执行完了instance = new
 * Singleton()后退出代码块，但是此时还没有真正初始化，
 * 这时线程B进来了，发现instance不为null，于是就立马返回该instance（其实是没有初始化好的），然后B就开始使用该instance，
 * 却发现没初始化，于是就出问题了。 原因: 说白了就是new不是一个原子操作，而且可能进行指令重排，并不一定是1，2，3顺序，可能是1，3，2。
 * 这样可能另一个线程在2的之前进入了。这样它直接return instance返回给其他函数使用，这个时候就会出错。所以要用volatile修饰
 * 
 * @author lenovopc
 *
 */
public class Singleton4Error {
	private static Singleton4Error instance;

	private Singleton4Error() {
	}

	public static Singleton4Error getInstance() {
		if (null == instance) {
			synchronized (instance) {
				if (null == instance) {
					instance = new Singleton4Error();
				}
			}
		}
		return instance;
	}
}
