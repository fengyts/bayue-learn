package learn.java.advanced.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 集合大量数据分段处理
 * @author lenovopc
 *
 */
public class BigListHandlerSection {

	public static void handle() throws Exception {
		int perThreadHandleSize = 100; // 每个线程处理数据量

		int totalDataSize = 110; // 数据总数
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < totalDataSize; i++) {
			data.add(i);
		}

		// 线程数
		int threadNum = totalDataSize / perThreadHandleSize;
		if (totalDataSize % perThreadHandleSize != 0) {
			threadNum += 1;
		}

		ExecutorService executor = Executors.newFixedThreadPool(threadNum);

		// 任务集合,
		// 每一个任务处理一组数据Callable<V>:V为每一组要处理的数据列表,此处模拟使用Integer,参见e-generator的ng.bayue.generator.gen.TableInfoHandler的
		// executeTask(...)方法
		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		Callable<Integer> task;
		List<Integer> perList;

		// 分割数据
		for (int i = 0; i < threadNum; i++) {
			if (i == threadNum - 1) {
				perList = data.subList(perThreadHandleSize * i, totalDataSize);
			} else {
				perList = data.subList(perThreadHandleSize * i, perThreadHandleSize * (i + 1));
			}

			final List<Integer> perListTask = perList;
			task = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					System.out.println(Thread.currentThread().getName() + "线程：" + perListTask);
					return 1;
				}
			};
			// 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
			tasks.add(task);
		}

		// 重要：若是处理完参数列表list，一定要处理结果集, 若不处理而是用方法参数列表，会出现数据错误
		List<Future<Integer>> results = executor.invokeAll(tasks);
		for (Future<Integer> future : results) {
			System.out.println(future.get());
		}
		// 关闭线程池
		executor.shutdown();

	}

	public static void main(String[] args) {
		try {
			handle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
