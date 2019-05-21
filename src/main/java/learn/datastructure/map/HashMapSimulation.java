package learn.datastructure.map;

import java.util.Objects;

/**
 * 模仿HashMap
 * 
 * @author lenovopc
 *
 */
public class HashMapSimulation<K, V> implements MapCustom<K, V> {

	/** 初始容量，16 */
	static final int DEFAULT_INITIALCAPACITY = 1 << 4;
	/** 默认加载因子 */
	static final float DEFAULT_LOADFACTOR = 0.75f;

	transient int size;

	/**
	 * 扩容阀值。 即 扩容阀值 = HashMap 总容量 * 加载因子。当前 HashMap 的容量大于或等于扩容阀值的时候就会去执行扩容。
	 * 扩容的容量为当前 HashMap 总容量的两倍。比如，当前 HashMap 的总容量为 16 ，那么扩容之后为 32 。
	 */
	transient int threshold;

	/**
	 * 加载因子。 所谓的加载因子就是 HashMap (当前的容量/总容量) 到达一定值的时候，HashMap 会实施扩容。
	 * 加载因子也可以通过构造方法中指定，默认的值是 0.75 。 举个例子，假设有一个 HashMap 的初始容量为 16 ，那么扩容的阀值就是
	 * 0.75 * 16 = 12 。也就是说，在你打算存入第 13 个值的时候，HashMap 会先执行扩容。
	 */
	final float loadFactor;

	static final Node<?, ?>[] EMPTY_TABLE = {};

	transient Node<K, V>[] table = (Node<K, V>[]) EMPTY_TABLE;

	public HashMapSimulation() {
		this.loadFactor = DEFAULT_LOADFACTOR;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void put(K k, V v) {
	}

	@Override
	public V get(Object key) {
		return null;
	}

	@Override
	public void remove(Object key) {
	}

	private static final int hash(Object key) {
		if (null == key) {
			return 0;
		}
		int h = key.hashCode();
		return h ^ (h >>> 16);
	}

	/**
	 * 扩容
	 */
	private void inflateTable() {
		if (table == EMPTY_TABLE) {
			threshold = DEFAULT_INITIALCAPACITY;
		}
	}

	static final int indexFor(int hash, int length) {
		return hash & (length - 1);
	}

	/**
	 * 扩容
	 */
	private void resize() {
	}

	/**
	 * 处理key=null
	 * 
	 * @param value
	 */
	private void putNullKey(V value) {
	}

	static class Node<K, V> implements MapCustom.Entry<K, V> {
		final K key;
		V value;
		Node<K, V> next;
		int hash;

		public Node(K key, V value, Node<K, V> next, int hash) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
			this.hash = hash;
		}

		@Override
		public final K getKey() {
			return key;
		}

		@Override
		public final V getValue() {
			return value;
		}

		@Override
		public final int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}

	}
	
	public static void main(String[] args) {
		String key = "abcdef";
		int hash = hash(key);
		int index = indexFor(hash, 16);
		
		System.out.println(index);
	}

}
