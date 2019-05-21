package learn.dubbo.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import learn.dubbo.business.IOrder;
import learn.dubbo.domain.Order;

/**
 * <pre>
 * 客户端
 * </pre>
 *
 * @author fengyts
 * @version $Id: Client.java, v 0.1 2019年5月2日 下午11:21:47 fengyts Exp $
 */
public class Client {

	/**
	 * <pre>
	 * 获取远程服务对象
	 * </pre>
	 *
	 * @param interfaceClass
	 *            远程接口类模板
	 * @param address
	 *            远程地址
	 * @param port
	 *            远程端口
	 * @return <T> 返回代理抽象对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> interfaceClass, String address, int port) {
		// 动态代理需要通过字节码重组，相当于spring AOP
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[] { interfaceClass }, new InvocationHandler() {

					/**
					 * @param proxy
					 * @param method
					 * @param args
					 * @return
					 * @throws Throwable
					 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
					 *      java.lang.reflect.Method, java.lang.Object[])
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						// 建立远程连接客户端socket
						Socket socket = new Socket(address, port);
						// 创建输出流对象
						ObjectOutputStream obp = new ObjectOutputStream(socket.getOutputStream());
						// 发送接口类名、方法名、方法参数类型对象、方法值
						obp.writeUTF(interfaceClass.getName());
						obp.writeUTF(method.getName());
						obp.writeObject(method.getParameterTypes());
						obp.writeObject(args);

						// 获取输入流
						ObjectInputStream objectInputStream = new ObjectInputStream(socket
								.getInputStream());
						// 获得远程返回结果
						Object object = objectInputStream.readObject();
						
						obp.close();
						objectInputStream.close();
						
						socket.close();

						return object;
						// return new Order((long) args[0], "dubbo 高并发的应用");
					}
				});
	}
	
	public static void testClient() {
		// 直连模式，用来做测试
		// 服务检测，启动检测，避免启动异常

		IOrder orderImplement = Client.getBean(IOrder.class, "localhost", 8989);
		Order order = orderImplement.selectById(1L);
		System.out.println(order);
		
		IOrder orderImplement2 = Client.getBean(IOrder.class, "localhost", 8999);
		Order order2 = orderImplement2.selectById(2L);
		System.out.println(order2);
	}
	public static void main(String[] args) {
		testClient();
	}

}
