package learn.java.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LeanHashMap {

	public void test() {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Map m = Collections.synchronizedMap(hashMap);
		
		ReentrantLock lock = new ReentrantLock();
	}

	public static void main(String[] args) {

	}

}
