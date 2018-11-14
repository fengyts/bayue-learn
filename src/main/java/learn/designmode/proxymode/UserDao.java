package learn.designmode.proxymode;

public class UserDao implements IUserDao{

	@Override
	public void save(Object o) {
		System.out.println("I'm target object, params: " + o.toString());
	}

}
