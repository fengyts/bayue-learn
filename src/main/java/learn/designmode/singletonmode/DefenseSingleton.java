package learn.designmode.singletonmode;

public class DefenseSingleton {

	private static DefenseSingleton instance;

	private DefenseSingleton() {
		// 防御单例模式通过反射方式来攻击
		if (instance != null) {
			throw new RuntimeException("the DefenseSingleton object is exist");
		}
	}

	public static DefenseSingleton getInstance() {
		if (null == instance) {
			instance = new DefenseSingleton();
		}
		return instance;
	}

}
