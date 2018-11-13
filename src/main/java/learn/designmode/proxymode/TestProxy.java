package learn.designmode.proxymode;

import learn.designmode.proxymode.dynamicproxy.ProxyDaoFactory;
import learn.designmode.proxymode.staticproxy.ProxyUserDao;

public class TestProxy {
	
	public static void testStaticProxy(){
		IUserDao targetObj = new UserDao();
		ProxyUserDao proxy = new ProxyUserDao(targetObj);
		proxy.save(new Object());
	}
	
	public static void testProxy(){
		IUserDao target = new UserDao();
		ProxyDaoFactory<IUserDao> proxy = new ProxyDaoFactory<IUserDao>(target);
		IUserDao proxyInstance = (IUserDao) proxy.getProxyInstance();
		proxyInstance.save(new Object());
	}
	
//	public static void testGetClass(){
//		ProxyDaoFactory<IUserDao> proxy = new ProxyDaoFactory<IUserDao>();
//		Class<IUserDao> c = (Class<IUserDao>) proxy.getClazz();
//		System.out.println(c);
//	}
	
//	public static void testProxy1(){
//		UserDao1 target1 = new UserDao1();
//		ProxyDaoFactory<UserDao1> proxy = new ProxyDaoFactory<UserDao1>(target1);
//		UserDao1 proxyInstance = (UserDao1) proxy.getProxyInstance();
//		proxyInstance.save(new Object());
//	}
	
	public static void main(String[] args) {
//		testStaticProxy();
		testProxy();
//		testGetClass();
	}

}
