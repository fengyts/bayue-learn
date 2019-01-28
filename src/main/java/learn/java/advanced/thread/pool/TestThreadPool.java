package learn.java.advanced.thread.pool;

import java.util.concurrent.ExecutorService;

public class TestThreadPool {

	public static void testThreadPool() {
		final ExecutorService execu = ThreadPool.getFixedThreadPool();
		execu.execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("thread1");
//					execu.shutdown();
					Thread.sleep(2000);
					System.out.println("thread1: "+ execu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		execu.shutdown();
		System.out.println(execu);
		execu.execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("thread2");
					Thread.sleep(2000);
					System.out.println("thread2: "+ execu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(execu);
		execu.shutdown();
		System.out.println(execu);
	}

	public static void main(String[] args) {
		testThreadPool();
	}

}
