package learn.designmode.singletonmode;

/**
 * 单例模式之思考扩展,经测试时非线程安全的
 * 
 * @author lenovopc
 *
 */
public final class SingletonThink {

	private SingletonThink() {
	}

	public static final SingletonThink instance = new SingletonThink();

}
