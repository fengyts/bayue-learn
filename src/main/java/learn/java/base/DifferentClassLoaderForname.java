package learn.java.base;

/**
 * Java 反射中，class.forName()和classLoader的区别:
 * 
 * 、java中class.forName()和classLoader都可用来对类进行加载。
 * 
 * 不同：
 * 1）class.forName()除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块，
 * 还会执行给静态变量赋值的静态方法 .
 * 2）classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,
 * 只有在newInstance才会去执行static块。 注：Class.forName(name, initialize,
 * loader)带参函数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象，附上Class.
 * 
 * forName()源码
 * 
 * @author lenovopc
 *
 */
public class DifferentClassLoaderForname {

	public static void testClassForName() {
		try {
			System.out.println("测试Class.forName方法：默认执行了static语句块...");
			Class<?> user = Class.forName(User.class.getName());
			System.out.println(user);

			// 不执行static语句块
			System.out.println("测试Class.forName方法:不执行static语句块...");
			Class<?> user1 = Class.forName(User.class.getName(), false, User.class.getClassLoader());
			System.out.println(user1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void testClassLoader() {
		try {
			System.out.println("\r\n测试ClassLoader...");
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			Class<?> user = loader.loadClass(User.class.getName());

			System.out.println(user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testClassForName();
		testClassLoader();
	}

}

class User {
	static {
		System.out.println("执行了static块");
	}
	{
		System.out.println("执行了非static语句块");
	}
}
