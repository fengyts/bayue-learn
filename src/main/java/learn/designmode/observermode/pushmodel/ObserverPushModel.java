package learn.designmode.observermode.pushmodel;

/**
 * 观察者接口-推送模式
 * 注意区分  拉取  模式的update方法的参数区别
 * @author lenovopc
 *
 */
public interface ObserverPushModel {
	
	/**
	 * 当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
	 */
	void update(String message);
	
	Object getConcreteObserverProp();

}
