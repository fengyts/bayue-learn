package learn.java.base;

import java.util.Objects;

public class EqualsTest {

	public static void stringEquals() {
		String s1 = new String("a");
		String s2 = new String("a");
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		
		Object o1 = new Object();
		Object o2 = new Object();
		System.out.println(o1.equals(o2));
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
		System.out.println(Objects.hashCode(o1));
	}

	public static void main(String[] args) {
		stringEquals();
	}

}
