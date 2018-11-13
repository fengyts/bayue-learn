package learn.designmode.singletonmode;

/**
 * 单例模式：饿汉式(立即加载模式)
 * 
 * @author lenovopc
 *
 */
public class Singleton3 {

	private static final Singleton3 instance = new Singleton3();

	private Singleton3() {
		if(null != instance){
			System.out.println("创建了其他对象");
		}
	}

	public static Singleton3 getInstance() {
		return instance;
	}

}
