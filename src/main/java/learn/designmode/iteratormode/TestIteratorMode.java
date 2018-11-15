package learn.designmode.iteratormode;

public class TestIteratorMode {

	public static void testIterator() {
		NameRepository nr = new NameRepository();
		Iterator it = nr.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void main(String[] args) {
		testIterator();
	}

}
