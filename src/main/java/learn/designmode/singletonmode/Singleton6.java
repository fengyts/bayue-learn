package learn.designmode.singletonmode;

public enum Singleton6 {
	INSTANCE;

	public void whatererMethod() {
		System.out.println("this is a singleton");
	}
	
	public static void main(String[] args) {
		Singleton6 instance = Singleton6.INSTANCE;
		instance.whatererMethod();
	}

}
