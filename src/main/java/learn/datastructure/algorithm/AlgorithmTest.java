package learn.datastructure.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlgorithmTest {

	public static void main(String[] args) {
		Integer[] array = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int key = 5;
		int index = (int) BinarySearch.binarySearch(array, key);
		System.out.println(index);

		List<Integer> list = Arrays.asList(array);
		int o = (int) BinarySearch.binarySearch(list, key);
		System.out.println(o);
		Arrays.binarySearch(array, key);

		Collections.binarySearch(list, key);

		List<User> users = new ArrayList<User>();
		Collections.binarySearch(users, key);
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
