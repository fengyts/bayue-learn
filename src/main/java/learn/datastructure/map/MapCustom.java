package learn.datastructure.map;

import java.util.Set;

public interface MapCustom<K, V> {

	int size();

	void put(K k, V v);

	V get(Object key);

	void remove(Object key);

	Set<Entry<K, V>> entrySet();

	interface Entry<K, V> {
		K getKey();

		V getValue();

		V setValue(V v);

		int hashCode();

		boolean equals(Object obj);

	}

}
