package learn.datastructure;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import learn.datastructure.map.HashMapSimulation;
import learn.datastructure.map.MapCustom;

/**
 * 数据结构测试
 * @author lenovopc
 *
 */
public class DataStructureTest {

	@Test
	public void testHashMapSimulation() {

		String key = "abc";
		String value = "abcdef";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		System.out.println(map);

		MapCustom<String, String> mc = new HashMapSimulation<String, String>();
		mc.put(key, value);

		System.out.println(mc);

		String str = mc.get(key);

		System.out.println(str);

	}

}
