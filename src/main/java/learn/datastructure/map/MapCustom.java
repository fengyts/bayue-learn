package learn.datastructure.map;

import java.util.Set;

public interface MapCustom<K, V> {

	int size();

	void put(K k, V v);

	V get(Object key);

	V remove(Object key);

	boolean isEmpty();

	boolean containsKey(Object key);

	void clear();

	Set<Entry<K, V>> entrySet();

	Set<K> keySet();

	interface Entry<K, V> {
		K getKey();

		V getValue();

		V setValue(V v);

		int hashCode();

		boolean equals(Object obj);

	}

}
