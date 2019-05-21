package learn.dubbo.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import learn.dubbo.business.IOrder;
import learn.dubbo.business.IOrderImpl;
import learn.dubbo.business.IOrderImpl2;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.objenesis.instantiator.basic.ObjectInputStreamInstantiator;

/**
 * <pre>
 * 服务端
 * </pre>
 *
 * @author fengyts
 * @version $Id: Server.java, v 0.1 2019年5月2日 下午10:07:41 fengyts Exp $
 */
public class Server {

	/** zookeeper服务根节点 */
	private static final String ZOOKEEPER_NODE_DUBBO = "/solomon";

	/** 服务接口实现存储容器 */
	private Map<String, Class> serverMap = new HashMap<String, Class>();

	/** 服务Socket */
	private ServerSocket serverSocket = null;

	/** 服务名称 */
	private String serverName;

	/** 线程池 */
	private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	/**
	 * <pre>
	 * 开始一个服务，核心方法
	 * </pre>
	 * 
	 * @throws IOException
	 *
	 */
	public void start(String address, int port, String serverName) throws IOException {
		serverSocket = new ServerSocket();
		serverSocket.setReuseAddress(true);
		serverSocket.setReceiveBufferSize(1024 * 10);
		serverSocket.setSoTimeout(1000 * 20);

		serverSocket.bind(new InetSocketAddress(address, port));
		Socket acceptSocket = null;
		System.out.println("server is start...");
		while ((acceptSocket = serverSocket.accept()) != null) {
			Socket finalAccept = acceptSocket;
			// 开启多线程
			executorService.execute(() -> {
				try {
					// 输入流
					ObjectInputStream objectInputStream = new ObjectInputStream(finalAccept.getInputStream());
					// 类名
					String className = objectInputStream.readUTF();
					// 方法名
					String methodName = objectInputStream.readUTF();
					// 方法参数类型
					Class<?>[] paramClasses = (Class[]) objectInputStream.readObject();
					// 方法参数值
					Object[] paramValues = (Object[]) objectInputStream.readObject();

					// 获得实例类对象
					Class<?> implementClass = serverMap.get(className);
					Object object = implementClass.newInstance();
					// 获得实例类对象的方法对象
					Method method = implementClass.getMethod(methodName, paramClasses);
					Object result = method.invoke(object, paramValues);

					// 输出流, 返回结果
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(finalAccept.getOutputStream());
					objectOutputStream.writeObject(result);

					// 关闭输入/输出流
					objectInputStream.close();
					objectOutputStream.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		}
	}

	/**
	 * <pre>
	 * 关闭一个服务
	 * </pre>
	 * 
	 * @throws IOException
	 *
	 */
	public void close() throws IOException {
		serverSocket.close();
	}

	/**
	 * <pre>
	 * 服务注册
	 * </pre>
	 *
	 * @param className
	 *            接口名称
	 * @param implementClass
	 *            接口实现类类型
	 */
	public void register(String className, Class<?> implementClass) {
		serverMap.put(className, implementClass);
	}

	/**
	 * <pre>
	 * test Server single:测试单个服务
	 * </pre>
	 *
	 */
	public static void testSingleServer() {
		try {
			Server server = new Server();
			// 注册服务1
			// server.register(IOrder.class.getName(), IOrderImpl.class);
			// server.start("localhost", 8989, "订单服务");

			// 注册多个服务
			server.register(IOrder.class.getName(), IOrderImpl2.class);
			server.start("localhost", 8999, "订单服务2");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 测试多个服务, 使用Zookeeper
	 * </pre>
	 *
	 */
	public static void testMultiServer() {
		try {
			// 加锁
			CountDownLatch countDownLatch = new CountDownLatch(1);
			ZooKeeper zookeeper = new ZooKeeper("localhost", 30 * 1000 * 60, event -> {
				// 如果已经连接上
					if (event.getState() != Watcher.Event.KeeperState.SyncConnected) {
						countDownLatch.countDown(); // 解锁
				}
			});
			countDownLatch.await(); // 等待

			// 注册服务
			// 如果根节点不存在则创建它
			if (zookeeper.exists(Server.ZOOKEEPER_NODE_DUBBO, false) == null) {
				zookeeper.create(Server.ZOOKEEPER_NODE_DUBBO, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);
			}

			String serverNodeNameTmp = "kaikeba";
			String serverNodeAddress = "localhost:8989";
			// 向zookeeper注册服务
			zookeeper.create(Server.ZOOKEEPER_NODE_DUBBO + "/" + serverNodeNameTmp, serverNodeAddress.getBytes(),
					ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

			// 注册/开启服务
			Server server = new Server();
			server.register(IOrder.class.getName(), IOrderImpl.class);
			server.start("localhost", 8989, "kaikeba");

		} catch (IOException | InterruptedException | KeeperException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testSingleServer();
	}

}
