package learn.designmode.proxymode;

import learn.designmode.proxymode.cglibproxy.CglibProxyFactory;
import learn.designmode.proxymode.cglibproxy.CglibUserDao;
import learn.designmode.proxymode.dynamicproxy.ProxyDaoFactory;
import learn.designmode.proxymode.staticproxy.ProxyUserDao;

public class TestProxy {

	public static void testStaticProxy() {
		IUserDao targetObj = new UserDao();
		ProxyUserDao proxy = new ProxyUserDao(targetObj);
		proxy.save(new Object());
	}

	/**
	 * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
	 * 这里ProxyDaoFactory是个代理，它所代理的对象是UserDao实现了接口IUserDao
	 */
	public static void testProxy() {
		IUserDao target = new UserDao();
		ProxyDaoFactory<IUserDao> proxy = new ProxyDaoFactory<IUserDao>(target);
		IUserDao proxyInstance = (IUserDao) proxy.getProxyInstance();
		proxyInstance.save(new Object());
	}

//	public static void testProxy1() {
//		UserDao1 target1 = new UserDao1();
//		ProxyDaoFactory<UserDao1> proxy = new ProxyDaoFactory<UserDao1>(target1);
//		UserDao1 proxyInstance = (UserDao1) proxy.getProxyInstance();
//		proxyInstance.save(new Object());
//	}

	public static void testCglibProxy() {
		// 目标对象
		CglibUserDao target = new CglibUserDao();
		// 代理对象
		CglibProxyFactory factory = new CglibProxyFactory(target);
		CglibUserDao proxy = (CglibUserDao) factory.getProxyInstance();
		// 执行代理对象的方法
		int r = proxy.save(new Object());
		System.out.println(r);
	}

	public static void main(String[] args) {
		// testStaticProxy();
		// testProxy();
		testCglibProxy();
	}

}
