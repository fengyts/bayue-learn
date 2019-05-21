package learn.generics;

public class GenericMemoryCell<T> {

	private T storedValue;

	public T getStoredValue() {
		return storedValue;
	}

	public void setStoredValue(T storedValue) {
		this.storedValue = storedValue;
	}

}
