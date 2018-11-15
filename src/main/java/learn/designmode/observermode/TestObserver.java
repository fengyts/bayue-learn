package learn.designmode.observermode;

import learn.designmode.observermode.pullmodel.ConcreteObserverPullModel;
import learn.designmode.observermode.pullmodel.ConcreteSubjectPullModel;
import learn.designmode.observermode.pullmodel.ObserverPullModel;
import learn.designmode.observermode.pullmodel.SubjectPullModel;
import learn.designmode.observermode.pushmodel.ConcreteObserverPushModel;
import learn.designmode.observermode.pushmodel.ConcreteSubjectPushModel;
import learn.designmode.observermode.pushmodel.ObserverPushModel;
import learn.designmode.observermode.pushmodel.SubjectPushModel;

public class TestObserver {
	
	/**
	 * 观察者模式之-推送模型
	 */
	public static void testObserverPush(){
		SubjectPushModel subject = new ConcreteSubjectPushModel();
		
		// 增加三个观察者
		ObserverPushModel o1 = new ConcreteObserverPushModel("观察者-张三");
		subject.addOberver(o1);
		ObserverPushModel o2 = new ConcreteObserverPushModel("观察者-李四");
		subject.addOberver(o2);
		ObserverPushModel o3 = new ConcreteObserverPushModel("观察者-王五");
		subject.addOberver(o3);
		
		String msg = "给观察者推送消息";
		subject.notifyObservers(msg);
		
		// 删除观察者-李四
		subject.removeObserver(o2);
		
		msg = "请注意：观察者-李四是内奸，已被删除";
		subject.notifyObservers(msg);
	}
	
	/**
	 * 观察者模式之-拉取模型
	 */
	public static void testObserverPull(){
		SubjectPullModel subject = new ConcreteSubjectPullModel();
		
		// 增加三个观察者
		ObserverPullModel o1 = new ConcreteObserverPullModel("观察者-张三");
		subject.addOberver(o1);
		ObserverPullModel o2 = new ConcreteObserverPullModel("观察者-李四");
		subject.addOberver(o2);
		ObserverPullModel o3 = new ConcreteObserverPullModel("观察者-王五");
		subject.addOberver(o3);
		
		String msg = "给观察者推送消息";
		subject.notifyObservers(msg);
		
		// 删除观察者-李四
		subject.removeObserver(o2);
		
		msg = "请注意：观察者-李四是内奸，已被删除";
		subject.notifyObservers(msg);
		
	}
	
	public static void main(String[] args) {
		testObserverPush();
//		testObserverPull();
	}

}
