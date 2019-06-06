package learn.datastructure.algorithm.sort;

import java.util.Arrays;

public class SortTest {

	public static void main(String[] args) {
		int[] arr = new int[] { 6, 2, 5, 1, 3 };
		SortUtil.bubbleSort(arr, true);
		System.out.println(Arrays.toString(arr));
		SortUtil.selectionSort(arr, false);
		System.out.println(Arrays.toString(arr));
	}

}
