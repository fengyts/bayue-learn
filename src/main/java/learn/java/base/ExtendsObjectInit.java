package learn.java.base;

public class ExtendsObjectInit {
	
	private static char cd; // char默认值为 '\u0000'
	private static boolean b = cd == '\u0000';
	
	public static void main(String[] args) {
		Child c = new Child();
		System.out.println(b);
	}

}


class Parent {

	static {
		System.out.println("加载父类静态块");
	}

	{
		System.out.println("加载父类代码块");
	}

	public Parent() {
		System.out.println("父类构造方法");
	}

}

class Child extends Parent {
	
	static {
		System.out.println("加载子类静态块");
	}
	{
		System.out.println("加载子类代码块");
	}

	public Child() {
		System.out.println("加载子类构造方法");
	}
}
