package learn.designmode.observermode.pullmodel;

import learn.designmode.observermode.pullmodel.ObserverPullModel;

/**
 * 被观察者接口-拉取模式
 * @author lenovopc
 *
 */
public interface SubjectPullModel {
	
	/**
	 * 增加一个观察者
	 * 
	 * @param o
	 */
	void addOberver(ObserverPullModel o);

	/**
	 * 删除一个观察者
	 * 
	 * @param o
	 */
	void removeObserver(ObserverPullModel o);

	/**
	 * 发送通知给所有的观察者
	 * 
	 * @param messages
	 */
	void notifyObservers(Object... messages);
	
	/**
	 * 被观察者携带的数据信息
	 * @return
	 */
	Object getData(int index);

}
