package learn.designmode.observermode.pushmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 具体的被观察者对象
 * 
 * @author lenovopc
 *
 */
public class ConcreteSubjectPushModel implements SubjectPushModel {

	/** 保存注册的观察者对象 */
	private List<ObserverPushModel> observers = new ArrayList<ObserverPushModel>();

	@Override
	public void addOberver(ObserverPushModel o) {
		observers.add(o);
		System.out.println("新增观察者:" + o.getConcreteObserverProp());
	}

	@Override
	public void removeObserver(ObserverPushModel o) {
		observers.remove(o);
		System.out.println("删除观察者:" + o.getConcreteObserverProp());
	}

	@Override
	public void notifyObservers(Object... messages) {
		String msg = Arrays.toString(messages);
		if (null == messages) {
			msg = "发送通知消息";
		}
		// 给所有观察者发送消息
		for (ObserverPushModel o : observers) {
			o.update(msg);
		}
	}

}
