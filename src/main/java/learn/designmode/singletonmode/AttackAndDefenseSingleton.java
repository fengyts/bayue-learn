package learn.designmode.singletonmode;

import java.lang.reflect.Constructor;

/**
 * 通过反射来攻击不安全的单例
 * 
 * @author lenovopc
 *
 */
public class AttackAndDefenseSingleton {

	/**
	 * 单例模式攻击:<link>Singleton2</link>和<link>Singleton3</link>都能通过反射来攻击
	 */
	public static void attact() {
		try {
			Singleton2 instance1 = Singleton2.getInstance();
			Singleton2 instance2 = null;
			Constructor<Singleton2> c = Singleton2.class.getDeclaredConstructor();
			c.setAccessible(true);
			instance2 = c.newInstance();

			System.out.println(instance1.hashCode());
			System.out.println(instance2.hashCode());
			// System.out.println(instance1);
			// System.out.println(instance2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 单例模式防御攻击
	 */
	public static void defense() {
		try {
			DefenseSingleton instance1 = DefenseSingleton.getInstance();
			System.out.println(instance1.hashCode());
			System.out.println(instance1);

			DefenseSingleton instance2 = null;
			Constructor<DefenseSingleton> c = DefenseSingleton.class.getDeclaredConstructor();
			c.setAccessible(true);
			instance2 = c.newInstance();

			System.out.println(instance2.hashCode());
			System.out.println(instance2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// attact();
		defense();
	}

}
