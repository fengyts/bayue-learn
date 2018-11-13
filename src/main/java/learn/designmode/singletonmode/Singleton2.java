package learn.designmode.singletonmode;

/**
 * 单例模式：懒汉式(延迟加载),线程不安全, 
 * 此方式可以通过反射来攻击，参考<link>AttackAndDefenseSingleton</link>,
 * 防御攻击参考：<link>DefenseSingleton</link>
 * @author lenovopc
 *
 */
public class Singleton2 {

	private static Singleton2 instance;

	private Singleton2() {
		if(null != instance){
			System.out.println("创建了其他对象");
		}
	}

	public static Singleton2 getInstance() {
		if (null == instance) {
			instance = new Singleton2();
		}
		return instance;
	}

}
