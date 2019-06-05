package learn.datastructure.algorithm;

import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 二分查找算法
 * </pre>
 *
 * @author fengyts
 * @version $Id: BinarySearch.java, v 0.1 2019年6月5日 下午9:40:06 fengyts Exp $
 */
public class BinarySearch {

	public static Object binarySearch(int[] array, int key) {
		int left = 0, right = array.length - 1; // 左右边界
		while (right >= left) {
			int mid = (right + left) >>> 1;// 中间下标,该操作等同于{(right + left)/2}
			int obj = array[mid];
			if (obj == key) {
				return mid;
			} else if (key < obj) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object binarySearch(Object[] arr, Object key) {
		int left = 0, right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			Comparable midObj = (Comparable) arr[mid];
			int cmp = midObj.compareTo(key);
			if (cmp < 0) {
				left = mid + 1;
			} else if (cmp > 0) {
				right = mid - 1;
			} else {
				return mid;
			}

		}
		return -1;
	}

	public static <T> Object binarySearch(List<? extends Comparable<? super T>> list, T key) {
		Object o = Collections.binarySearch(list, key);
		return o;
	}

}
