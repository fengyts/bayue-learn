package learn.designmode.singletonmode;

public class Singleton2Thread extends Thread {

	@Override
	public void run() {
		try {
			// Thread.sleep(1000);
			System.out.println(Singleton2.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
