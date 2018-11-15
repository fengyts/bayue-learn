package learn.designmode.observermode.pullmodel;

public class ConcreteObserverPullModel implements ObserverPullModel {
	
	private String name;
	
	public ConcreteObserverPullModel(String name){
		this.name = name;
	}
	
	@Override
	public void update(SubjectPullModel subject) {
		int index = 0;
		if("观察者-张三".equals(name)){
			index = 0;
		}
		if("观察者-李四".equals(name)){
			index = 1;
		}
		if("观察者-王五".equals(name)){
			index = 2;
		}
		System.out.println(name + "收到通知，我要获取我想要的信息：" + subject.getData(index));
	}

	@Override
	public Object getConcreteObserverProp() {
		return name;
	}

}
