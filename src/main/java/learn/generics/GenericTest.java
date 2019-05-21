package learn.generics;

public class GenericTest {

	public static void testGeneric() {
		GenericMemoryCell<Integer> cell1 = new GenericMemoryCell<Integer>();
		cell1.setStoredValue(1);
		Object cell = cell1;
		GenericMemoryCell<String> cell2 = (GenericMemoryCell<String>) cell;
		String s1 = String.valueOf(cell2.getStoredValue());
		System.out.println(s1);
		String s = cell2.getStoredValue();
		System.out.println(s);
	}
	
	public static void testAutoPackage(){
		int a = 1;
		Object o = a;
		System.out.println(o instanceof Integer);
	}

	public static void main(String[] args) {
	}

}
