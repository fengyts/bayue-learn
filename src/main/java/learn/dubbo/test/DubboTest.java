package learn.dubbo.test;

import java.io.IOException;

import learn.dubbo.business.IOrder;
import learn.dubbo.business.IOrderImpl;
import learn.dubbo.client.Client;
import learn.dubbo.domain.Order;
import learn.dubbo.server.Server;

public class DubboTest {

	/**
	 * <pre>
	 * test Server
	 * </pre>
	 *
	 */
	public static void testServer() {
		try {
			Server server = new Server();
			server.register(IOrder.class.getName(), IOrderImpl.class);
			server.start("localhost", 8989, "订单服务");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * test Client
	 * </pre>
	 *
	 */
	public static void testClient() {
		// 直连模式，用来做测试
		// 服务检测，启动检测，避免启动异常

		IOrder orderImplement = Client.getBean(IOrder.class, "localhost", 8989);
		Order order = orderImplement.selectById(1L);
		System.out.println(order);
	}

	public static void main(String[] args) {
		try {
			testServer();
			Thread.sleep(2000);
			testClient();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
