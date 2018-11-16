package learn.java.advanced.dateformate;

import java.text.ParseException;

public class TestSafeDateFormat {
	
	private static boolean exception = true;

	public static class TestSimpleDateFormatThreadSafe extends Thread {
		@Override
		public void run() {
			while (exception) {
				try {
					this.join(2000);
				} catch (InterruptedException e1) {
					exception = false;
					e1.printStackTrace();
				}
				try {
//					Thread.sleep(200);
					System.out.println(this.getName() + ":" + DateUtil2.parse("2013-05-24 06:02:20"));
				} catch (ParseException e) {
					exception = false;
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new TestSimpleDateFormatThreadSafe().start();
		}

	}

}
