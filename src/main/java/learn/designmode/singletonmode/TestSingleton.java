package learn.designmode.singletonmode;

public class TestSingleton {

	public static void testSingleton2() {
		Singleton2Thread t1 = new Singleton2Thread();
		Singleton2Thread t2 = new Singleton2Thread();
		Singleton2Thread t3 = new Singleton2Thread();
		Singleton2Thread t4 = new Singleton2Thread();

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			testSingleton2();
		}
	}

}
