package learn.datastructure.map;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * 模仿HashMap
 * 
 * @author lenovopc
 *
 */
public class HashMapSimulation<K, V> extends AbstractMap<K, V> implements MapCustom<K, V> {

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
		for (Node<K, V> bucket = table[index]; bucket != null; bucket = bucket.next) {
			Object ok = bucket.key;
			// key值存在，覆盖
			if (bucket.hash == hash && (ok == bucket.key || ok.equals(bucket.key))) {
				@SuppressWarnings("unused")
				V oldV = bucket.value;
				bucket.value = v;
				return;
			}
		}
		// key值不存在，创建新的
		addNode(hash, k, v, index);
	}

	void addNode(int hash, K k, V v, int bucketIndex) {
		// 判断是否需要扩容,当当前四size没有达到阈值threshold并且当前桶的位置不为空的时候，需要扩容
		if (size > threshold && null != table[bucketIndex]) {
			resize();
			// 从新计算扩容之后key的hash和桶的位置
			hash = hash(k);
			bucketIndex = indexFor(hash, table.length);
		}
		// 创建新的链表节点
		createNewNode(hash, k, v, bucketIndex);
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
	public V remove(Object key) {
		return null;
	}

	@Override
	public void clear() {
		Node<K, V>[] tem = table;
		int len;
		if (tem != null && (len = tem.length) > 0) {
			size = 0;
			for (int i = 0; i < len; i++) {
				tem[i] = null;
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return null != getEntry(key);
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

	/**
	 * 计算桶下标
	 * @param hash
	 * @param length
	 * @return
	 */
	static final int indexFor(int hash, int length) {
		return hash & (length - 1);
	}

	@SuppressWarnings("unchecked")
	private void inflateTable() {
		if (null == table || table.length == 0) {
			int capacity = DEFAULT_INITIAL_CAPACITY;
			threshold = (int) (loadFactor * capacity);
			table = new Node[capacity];
		}
	}

	/**
	 * 扩容
	 */
	private Node<?, ?>[] resize() {
		Node<K, V>[] oldTable = table;
		int oldCapacity = oldTable.length;
		if (oldCapacity >= MAXIMUM_CAPACITY) {
			return oldTable;
		}
		int newCapacity = 2 * oldCapacity;
		@SuppressWarnings("unchecked")
		Node<K, V>[] newTable = new Node[newCapacity];
		threshold = (int) (loadFactor * newCapacity);
		transferData(newTable);
		// table = newTable;
		return newTable;
	}

	/**
	 * 扩容时旧table数据移动到新table中
	 * @param newTable
	 */
	private void transferData(Node<K, V>[] newTable) {
		int oldCapacity = newTable.length;
		for (Node<K, V> old : table) {
			while (old != null) {
				Node<K, V> e = old.next;
				int hash = null == old.key ? 0 : hash(old.key);
				old.hash = hash;
				int index = indexFor(hash, oldCapacity);
				old.next = newTable[index];
				newTable[index] = old;
				old = e;
			}
		}
		table = newTable;
	}

	/**
	 * 处理key=null, 空key值默认放在0桶
	 * 
	 * @param value
	 */
	private V putNullKey(V value) {
		for (Node<K, V> e = table[0]; e != null; e = e.next) {
			if (null == e.key) {
				V oldV = e.value;
				e.value = value;
				return oldV;
			}
		}
		addNode(0, null, value, 0);
		return null;
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
		Set<Entry<K, V>> es;
		return null == (es = entrySet) ? (entrySet = new EntrySet()) : es;
	}

	@Override
	public Set<K> keySet() {
		Set<K> ks;
		return null == (ks = keySet) ? (keySet = new KeySet()) : ks;
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

	abstract class HashIterator<E> implements Iterator<E> {
		Node<K, V> current;
		Node<K, V> next;
		int index;

		HashIterator() {
			Node<K, V>[] tem = table;
			current = next = null;
			index = 0;
			if (tem != null && size > 0) {
				do {
				} while (index < tem.length && (next = tem[index++]) == null);
			}
		}

		public final boolean hasNext() {
			return next != null;
		}

		public final Node<K, V> nextNode() {
			Node<K, V>[] t;
			Node<K, V> e = next;
			if ((next = (current = e).next) == null && (t = table) != null) {
				do {
				} while (index < t.length && (next = t[index++]) == null);
			}
			return e;
		}
	}

	final class EntryIterator extends HashIterator<Entry<K, V>> {
		@Override
		public Entry<K, V> next() {
			return nextNode();
		}
	}

	final class KeyIterator extends HashIterator<K> {
		@Override
		public K next() {
			return nextNode().key;
		}
	}

	class EntrySet extends AbstractSet<Entry<K, V>> {
		@Override
		public void clear() {
			HashMapSimulation.this.clear();
		}

		@Override
		public final Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}

		@Override
		public int size() {
			return size;
		}
	}

	class KeySet extends AbstractSet<K> {
		@Override
		public void clear() {
			HashMapSimulation.this.clear();
		}

		@Override
		public Iterator<K> iterator() {
			return new KeyIterator();
		}

		@Override
		public int size() {
			return size;
		}
	}

}
