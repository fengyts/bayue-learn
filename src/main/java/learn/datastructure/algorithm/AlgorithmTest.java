package learn.datastructure.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlgorithmTest {

	public static void main(String[] args) {
		Integer[] array = new Integer[] { 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10 };
		int[] arrayB = new int[] { 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10 };
		int len = array.length;
		int key = 2;
		int index = BinarySearch.binarySearch(array, key);
		System.out.println(index);

		List<Integer> list = Arrays.asList(array);
		int o = BinarySearch.binarySearch(list, key);
		System.out.println(o);
		Arrays.binarySearch(arrayB, key);

		int i = Collections.binarySearch(list, key);
		System.out.println(i);
		List<User> users = new ArrayList<User>();
		int i1 = Collections.binarySearch(users, key);
		System.out.println(i1);

		int firstEq = BinarySearch.findFirstEqual(arrayB, key);
		int firstEq1 = BinarySearch.findFirstEqual(array, key);

		System.out.println("firstEq:" + firstEq);
		System.out.println("firstEq1:" + firstEq1);

		int lastEq = BinarySearch.findLastEqual(arrayB, key);
		System.out.println("lastEq:" + lastEq);

	}

	class User implements Comparable<Integer> {

		private Integer id;
		private String name;

		@Override
		public int compareTo(Integer o) {
			return 0;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
