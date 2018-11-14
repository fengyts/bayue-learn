package learn.designmode.proxymode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDaoFactory<T> {
	
	private T target;

	public ProxyDaoFactory(T target) {
		super();
		this.target = target;
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
