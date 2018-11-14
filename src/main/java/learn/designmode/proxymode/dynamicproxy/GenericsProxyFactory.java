package learn.designmode.proxymode.dynamicproxy;

import java.lang.reflect.ParameterizedType;

public abstract class GenericsProxyFactory<T> {

	private T target;

	private Class<T> objectType;

	public GenericsProxyFactory() {
		getObjectType();
//		this.objectType = objectType;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getObjectType() {
		objectType = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return objectType;
	}

	public T getObject() {
		try {
			target = objectType.newInstance();
			return target;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T getTarget() {
		return target;
	}

	public void setTarget(T target) {
		this.target = target;
	}

}
