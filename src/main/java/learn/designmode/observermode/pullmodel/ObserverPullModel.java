package learn.designmode.observermode.pullmodel;

/**
 *  观察者接口-拉取模式
 *  注意区分  推送  模式的update方法的参数区别
 * @author lenovopc
 *
 */
public interface ObserverPullModel {
	
	/**
	 * 当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
	 * @param subject
	 */
	void update(SubjectPullModel subject);
	
	Object getConcreteObserverProp();

}
