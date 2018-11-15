package learn.designmode.observermode.pushmodel;

public class ConcreteObserverPushModel implements ObserverPushModel {

	/** 观察者名称 */
	private String name;

	public ConcreteObserverPushModel(String name) {
		this.name = name;
	}

	@Override
	public void update(String message) {
		System.out.println(name + "收到通知：" + message);
	}

	@Override
	public Object getConcreteObserverProp() {
		return name;
	}

}
