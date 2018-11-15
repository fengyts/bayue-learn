package learn.designmode.observermode.pushmodel;

/**
 * 被观察者接口-推送模式
 * 
 * @author lenovopc
 *
 */
public interface SubjectPushModel {

	/**
	 * 增加一个观察者
	 * 
	 * @param o
	 */
	void addOberver(ObserverPushModel o);

	/**
	 * 删除一个观察者
	 * 
	 * @param o
	 */
	void removeObserver(ObserverPushModel o);

	/**
	 * 发送通知给所有的观察者
	 * 
	 * @param messages
	 */
	void notifyObservers(Object... messages);

}
