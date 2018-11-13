package learn.designmode.proxymode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDaoFactory<T> extends Object {

	private T target;
//	private Class<T> clazz;

	public ProxyDaoFactory(T t) {
		this.target = t;
	}

	public ProxyDaoFactory() {
	}

	@SuppressWarnings("unchecked")
	public T getProxyInstance() {
		final Class<T> clazz = (Class<T>) target.getClass();
		InvocationHandler h = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("开始执行被代理目标方法:");
				Object returnValue = method.invoke(clazz.newInstance(), args);
				System.out.println("do other something...");
				return returnValue;
			}

		};
		T t = (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), h);
		return t;
	}

}
