package learn.designmode.proxymode.cglibproxy;

/**
 * cglib代理：被代理对象UserDao没有实现任何接口或继承父类
 * @author lenovopc
 *
 */
public class CglibUserDao {
	
	public int save(Object o){
		System.out.println("这里是被代理的对象");
		return 1;
	}

}
