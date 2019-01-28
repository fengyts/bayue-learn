package learn.java.advanced.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadPool {
	private static final int POOL_SIZE = 8;

	private static volatile ExecutorService fixedThreadPool;

	public static ExecutorService getFixedThreadPool() {
		if (null == fixedThreadPool) {
			synchronized (ThreadPool.class) {
				if (null == fixedThreadPool) {
					fixedThreadPool = Executors.newFixedThreadPool(POOL_SIZE);
				}
			}
		}
		return fixedThreadPool;
	}

}
