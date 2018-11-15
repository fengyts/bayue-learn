package learn.designmode.observermode.pullmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcreteSubjectPullModel implements SubjectPullModel {

	/** 保存注册的观察者对象 */
	private List<ObserverPullModel> observers = new ArrayList<ObserverPullModel>();

	/** 观察者携带的数据信息  */
	private List<String> dataInfo = Arrays.asList(new String[] { "我是张三想要的数据", "我是李四想要的数据", "我是王五想要的数据", "我是其它数据4" });

	@Override
	public void addOberver(ObserverPullModel o) {
		observers.add(o);
		System.out.println("新增观察者:" + o.getConcreteObserverProp());
	}

	@Override
	public void removeObserver(ObserverPullModel o) {
		observers.remove(o);
		System.out.println("删除观察者:" + o.getConcreteObserverProp());
	}

	@Override
	public void notifyObservers(Object... messages) {
		// 给所有观察者发送消息
		for (ObserverPullModel o : observers) {
			o.update(this);
		}
	}

	@Override
	public Object getData(int index) {
		return dataInfo.get(index);
	}

}
