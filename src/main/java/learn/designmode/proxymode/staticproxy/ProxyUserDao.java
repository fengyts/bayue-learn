package learn.designmode.proxymode.staticproxy;

import learn.designmode.proxymode.IUserDao;

public class ProxyUserDao implements IUserDao {

	private IUserDao userDao;

	public ProxyUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(Object o) {
		System.out.println("proxy object do something...");
		userDao.save(o);
	}

}
