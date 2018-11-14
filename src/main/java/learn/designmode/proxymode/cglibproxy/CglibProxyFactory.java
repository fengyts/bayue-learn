package learn.designmode.proxymode.cglibproxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxyFactory implements MethodInterceptor {

	/** 被代理的对象 */
	private Object target;

	public CglibProxyFactory(Object target) {
		this.target = target;
	}

	// 给目标对象创建一个代理对象
	public Object getProxyInstance() {
		// 1.工具类
		Enhancer en = new Enhancer();
		// 2.设置父类
		en.setSuperclass(target.getClass());
		// 3.设置回调函数
		en.setCallback(this);
		// 4.创建子类(代理对象)
		Object create = en.create();
		return create;

	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("执行代理方法前...");
		// 执行目标对象的方法
		// 这里需要特别注意----巨坑----
		// 下面三种写法是正确的(returnValue,returnValue1,returnValue2)
		// (returnValueError1,returnValueError2,returnValueError3均是错误写法，会出现死循环)
		// 区别：注意方法里面的obj和target分别
		Object returnValue = method.invoke(target, args);
		// Object returnValue1 = methodProxy.invoke(target, args);
		// Object returnValue2 = methodProxy.invokeSuper(obj, args);
		
		//下面三种是错误写法，巨坑巨坑巨坑。。。。。。。
		// Object returnValueError1 = method.invoke(obj, args);
		// Object returnValueError2 = methodProxy.invoke(obj, args);
		// Object returnValueError3 = methodProxy.invokeSuper(target, args);

		System.out.println("执行代理方法后...");

		return returnValue;
	}

}
