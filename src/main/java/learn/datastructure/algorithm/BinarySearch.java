package learn.datastructure.algorithm;

import java.lang.reflect.Array;
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
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BinarySearch {

	public static int binarySearch(int[] array, int key) {
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

	public static int binarySearch(Object[] arr, Object key) {
		int left = 0, right = arr.length - 1; // 左右边界
		while (left <= right) {
			int mid = (left + right) >>> 1; // 中间下标,该操作等同于{(right + left)/2}
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

	/**
	 * list 中的对象必须实现了Comparable接口
	 * @param list
	 * @param key
	 * @return
	 */
	public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
		int i = Collections.binarySearch(list, key);
		return i;
	}

	/**
	 * 通过反射复制一个新数组，关键类：<link>java.lang.reflect.Array</link>
	 * @param arrayObj: 非基础类型的数组对象
	 * @param arrayLength
	 * @return
	 */
	private static <T> T[] arrayConvertToObject(Object arrayObj, int arrayLength) {
		Object resArray = null;
		Class<? extends Object> clazz = arrayObj.getClass();
		if (clazz.isArray()) {
			Class<?> componentType = clazz.getComponentType(); // 获取数组代表的实际类型
			resArray = Array.newInstance(componentType, arrayLength);
			System.arraycopy(arrayObj, 0, resArray, 0, arrayLength);
		}
		return (T[]) resArray;
	}

	//=============  二分查找变种(varianty)   =================

	//	关于二分查找，如果条件稍微变换一下，比如：数组之中的数据可能可以重复，要求返回匹配的数据的最小（或最大）的下标；
	//	更近一步， 需要找出数组中第一个大于key的元素（也就是最小的大于key的元素的）下标，等等。
	//	这些，虽然只有一点点的变化，实现的时候确实要更加的细心。
	//	二分查找的变种和二分查找原理一样，主要就是变换判断条件（也就是边界条件），如果想直接看如何记忆这些变种的窍门，请直接翻到本文最后。
	//	下面来看几种二分查找变种的代码：

	/**
	 * 二分查找变种1：
	 * 查找第一个与key相等的元素
	 * @return
	 */
	public static int findFirstEqual(Object[] array, Object key) {
		final int length = array.length;
		int left = 0, right = length - 1;

		while (left <= right) {
			int mid = (left + right) >>> 1;
			Comparable midVal = (Comparable) array[mid];
			int cmp = midVal.compareTo(key);
			if (cmp >= 0) {// 关键代码，必须 >=
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		Comparable leftVal = (Comparable) array[left];
		if (left < length && leftVal.compareTo(key) == 0) {
			return left;
		}
		return -1;
	}

	public static int findFirstEqual(int[] array, int key) {
		int left = 0, right = array.length - 1;
		// 这里必须是 <=
		while (left <= right) {
			int mid = (left + right) / 2;
			if (array[mid] >= key) { // 这里必须是 >=
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		if (left < array.length && array[left] == key) {
			return left;
		}

		return -1;
	}

	/**
	 * 二分查找变种2：
	 * 查找最后一个与key相等的元素
	 * @return
	 */
	public static int findLastEqual(int[] array, int key) {
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			if (array[mid] <= key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (right >= 0 && array[right] == key) {
			return right;
		}

		return -1;
	}

}
