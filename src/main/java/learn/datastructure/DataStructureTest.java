package learn.datastructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import learn.datastructure.map.HashMapSimulation;
import learn.datastructure.map.MapCustom;
import learn.datastructure.stack.StackSimulation;

/**
 * 数据结构测试
 * 
 * @author lenovopc
 *
 */
public class DataStructureTest {

	@Test
	public void testHashMapSimulation() {

		String key = "abc";
		String value = "abcdef";
		String key1 = "bbb";
		String value1 = "bbbb1";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		map.put(key1, value1);
		System.out.println(map);

		MapCustom<String, String> mc = new HashMapSimulation<String, String>();
		mc.put(key, value);

		mc.put(key1, value1);

		System.out.println(mc);

		String str = mc.get(key);
		System.out.println(str);

		System.out.println(mc.get(key1));

		for (char i = 97; i < 123; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
			mc.put(String.valueOf(i), String.valueOf(i + 1));
		}
		// {11=11, 12=12, 13=13, 14=14, abc=abcdef, bbb=bbbb1, 15=15, 16=16,
		// 17=17, 18=18, 19=19, 0=0, 1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8,
		// 9=9, 10=10}
		System.out.println("mc" + mc);
		System.out.println("map" + map);
		System.out.println(mc.get("b"));

		Set<String> keySet = mc.keySet();
		for (String k : keySet) {
			System.out.print(k + " ");
		}
		System.out.println();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();

		map.clear();
		System.out.println(map);
		mc.clear();
		System.out.println(mc);

	}

	@Test
	public void TestStack() {
		Stack<String> stack = new Stack<>();
		StackSimulation<String> ss = new StackSimulation<>();
		for (int i = 97; i < 105; i++) {
			stack.push(String.valueOf((char) i));
			ss.push(String.valueOf((char) i));
		}
		System.out.println(stack);
		System.out.println(ss.toString());

		String pop = stack.pop();
		String popss = ss.pop();
		System.out.println(pop + "--popMy:" + popss);
		System.out.println(stack);
		System.out.println(ss.toString());
		
		System.out.println(stack.size());
		System.out.println(ss.size());
	}

}
