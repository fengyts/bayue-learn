package learn.datastructure.map;

import java.util.Objects;
import java.util.Set;

import learn.datastructure.map.MapCustom.Entry;

/**
 * 模仿HashMap
 * 
 * @author lenovopc
 *
 */
public class HashMapSimulation<K, V> implements MapCustom<K, V> {

	/** 初始容量，16 */
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
	/** 默认加载因子 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	/** 最大容量 */
	static final int MAXIMUM_CAPACITY = 1 << 30;

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

	@SuppressWarnings("unchecked")
	transient Node<K, V>[] table = (Node<K, V>[]) EMPTY_TABLE;

	transient Set<Entry<K, V>> entrySet;

	public HashMapSimulation() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void put(K k, V v) {
		if (null == k) {
			putNullKey(v);
		}
		inflateTable();
		int hash = hash(k);
		int length = table.length;
		int index = indexFor(hash, length);
		Node<K, V> bucket = table[index];
		if (null == bucket) {
			createNewNode(hash, k, v, index);
		}
	}

	void createNewNode(int hash, K k, V v, int bucketIndex) {
		Node<K, V> oldNode = table[bucketIndex];
		Node<K, V> newNode = new Node<>(k, v, oldNode, hash);
		table[bucketIndex] = newNode;
		size++;
	}

	@Override
	public V get(Object key) {
		if (null == key) {
			return getNullKey();
		}
		Node<K, V> entry = getEntry(key);
		return null == entry ? null : entry.getValue();
	}

	private Node<K, V> getEntry(Object key) {
		if (size == 0) {
			return null;
		}
		int hash = null == key ? 0 : hash(key);
		Node<K, V> bks = table[indexFor(hash, table.length)];
		Object k;
		for (Node<K, V> n = bks; n != null; n = n.next) {
			k = n.key;
			if (hash == n.hash && (hash == n.hash && (k == key || (k != null && k.equals(key))))) {
				return n;
			}
		}

		return null;
	}

	@Override
	public void remove(Object key) {
	}

	/**
	 * 计算key的hash值
	 * @param key
	 * @return
	 */
	private static final int hash(Object key) {
		if (null == key) {
			return 0;
		}
		int h = key.hashCode();
		return h ^ (h >>> 16);
	}

	static final int indexFor(int hash, int length) {
		return hash & (length - 1);
	}

	/**
	 * 扩容
	 */
	private Node<?, ?>[] inflateTable() {
		// 1. 计算出新的table的容量和扩容阈值
		// 先判断容量是否达到最大值，若达到最大值，不扩容，返回旧的table
		// 判断是否达到扩容阈值，未达到则不扩容
		Node<K, V>[] oldTable = table;
		int oldCapacity = oldTable == null ? 0 : table.length;
		int oldThreshold = threshold;
		int newThreshold = 0, newCapacity;
		if (oldCapacity > 0) {
			if (oldCapacity >= MAXIMUM_CAPACITY) {
				threshold = Integer.MAX_VALUE;
				return oldTable;
			}
			newCapacity = oldCapacity << 1;
			if (newCapacity < MAXIMUM_CAPACITY && oldCapacity >= DEFAULT_INITIAL_CAPACITY) {
				newThreshold = oldThreshold << 1;// 扩容两倍
			}
		} else {
			newCapacity = DEFAULT_INITIAL_CAPACITY;
			newThreshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		}

		if (newThreshold == 0) {
			float newFactor = newCapacity * loadFactor;
			newThreshold = (int) newFactor;
		}
		threshold = newThreshold;

		// 创建新的table
		@SuppressWarnings("unchecked")
		Node<K, V>[] newTable = new Node[newCapacity];
		transferData(newTable);
		return newTable;
	}

	/**
	 * 扩容时旧table数据移动到新table中
	 * @param newTable
	 */
	private void transferData(Node<K, V>[] newTable) {
		table = newTable;
	}

	/**
	 * 扩容
	 */
	private Node<?, ?>[] resize() {
		return null;
	}

	/**
	 * 处理key=null
	 * 
	 * @param value
	 */
	private void putNullKey(V value) {
	}

	private V getNullKey() {
		if (0 == size) {
			return null;
		}
		for (Node<K, V> n = table[0]; n != null; n = n.next) {
			if (n.key == null) {
				return n.value;
			}
		}
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return null;
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
		public V setValue(V v) {
			V oldValue = v;
			value = v;
			return oldValue;
		}

		@Override
		public final int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}

		@Override
		public final boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (obj instanceof Entry) {
				Entry<?, ?> entry = (Entry<?, ?>) obj;
				if (Objects.equals(key, entry.getKey()) && Objects.equals(value, entry.getValue())) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return " [key=" + key + ", value=" + value + "]";
		}

	}

	public static void main(String[] args) {
		String key = "abcdef";
		int hash = hash(key);
		int index = indexFor(hash, 16);

		System.out.println(index);
	}

}
