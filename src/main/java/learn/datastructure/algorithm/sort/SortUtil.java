package learn.datastructure.algorithm.sort;

/**
 * 排序工具
 * @author lenovopc
 *
 */
public class SortUtil {

	/**
	 * 冒泡排序
	 * @param arr
	 * @param sortType 排序类型：true-升序(默认),false-降序
	 */
	public static void bubbleSort(int[] arr, boolean sortType) {
		final int len = arr.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				boolean st = false;
				if (sortType) {
					st = arr[j] > arr[j + 1]; // 升序
				} else {
					st = arr[j] < arr[j + 1]; // 降序
				}
				if (st) {
					int tem = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tem;
				}
			}
		}
	}

	/**
	 * 选择排序
	 * 算法：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
	 * 以此类推，直到所有元素均排序完毕。 
	 * @param arr
	 * @param sortType 排序类型：true-升序(默认),false-降序
	 */
	public static void selectionSort(int[] arr, boolean sortType) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			int minIndex = i; // 最小值得索引
			for (int j = i; j < len; j++) {
				if (arr[minIndex] > arr[j]) {
					minIndex = j; // 返回最小值的索引
				}
			}
			int tem = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = tem;
		}
	}

	/**
	 * 插入排序
	 * 一般来说，插入排序都采用in-place在数组上实现。
	 * 具体算法描述如下：
	 * 从第一个元素开始，该元素可以认为已经被排序；
	 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
	 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
	 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
	 * 将新元素插入到该位置后；
	 * 重复步骤2~5。
	 * @param arr
	 * @param sortType
	 */
	public static void insertionSort(int[] arr, boolean sortType) {
		final int len = arr.length;
		int current;
		for (int i = 0; i < len - 1; i++) {
			current = arr[i + 1];// 假设第一个是有序的,从第二个开始往前插
			int preIndex = i;// 记录当前值current的前一个的索引
			int preIndexVal = arr[preIndex];
			while (preIndex >= 0 && current < preIndexVal) {
				arr[preIndex + 1] = preIndexVal; // 不满足条件的往后挪移，给current值腾出正确位置
				preIndex--;
			}
			// while里面preIndex是自减的,所以此处是arr[preIndex + 1]
			arr[preIndex + 1] = current;
		}
	}

	/**
	 * <pre>
	 * 希尔排序
	 * </pre>
	 *
	 * @param arr
	 * @param sortType
	 */
	public static void shellSort(int[] arr, boolean sortType) {
	}

}
