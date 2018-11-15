package learn.designmode.iteratormode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameRepository implements Container {

	private List<String> nameList = new ArrayList<String>(Arrays.asList("张三", "李四", "王五", "赵六"));
	// 普及List或集合快速初始化赋值
	// nameList1 长度固定,nameList1.add("")会报错
	// private List<String> nameList1 = Arrays.asList("张三", "李四", "王五", "赵六");
	// nameList2 这种初始化存在序列化问题,不推荐使用
	// private List<String> nameList2 = new ArrayList<String>() {
	// 	{
	// 		add("a");
	// 		add("b");
	// 	}
	// };

	@Override
	public Iterator iterator() {
		return new NameIterator();
	}

	private class NameIterator implements Iterator {
		int index;

		@Override
		public boolean hasNext() {
			return index < nameList.size();
		}

		@Override
		public String next() {
			if (hasNext()) {
				return nameList.get(index++);
			}
			return null;
		}
	}

}
