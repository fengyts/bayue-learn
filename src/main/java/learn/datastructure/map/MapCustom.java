package learn.datastructure.map;

public interface MapCustom<K, V> {

	int size();

	void put(K k, V v);

	V get(Object key);

	void remove(Object key);

	interface Entry<K, V> {
		K getKey();

		V getValue();
		
		int hashCode();
	}

}
