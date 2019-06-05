package learn.datastructure.stack;

import java.util.Arrays;
import java.util.RandomAccess;

/**
 * <pre>
 * 模拟栈结构
 * </pre>
 *
 * @author fengyts
 * @version $Id: StackSimulation.java, v 0.1 2019年6月5日 下午10:37:28 fengyts Exp $
 */
public class StackSimulation<E> implements RandomAccess, Cloneable, java.io.Serializable {

	private Object[] elementData;
	private int top;

	public StackSimulation() {
		this.elementData = new Object[10];
		top = -1;
	}

	/**
	 * <pre>
	 * 放入数据
	 * </pre>
	 *
	 */
	public void push(E e) {
		elementData[++top] = e;
	}

	/**
	 * <pre>
	 * 弹出栈顶数据, 删除
	 * </pre>
	 *
	 * @return
	 */
	public synchronized E pop() {
		E e = peek();
		elementData[top--] = null;
		return e;
	}

	/**
	 * <pre>
	 * 查看栈顶数据
	 * </pre>
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized E peek() {
		return (E) elementData[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int size() {
		return top + 1;
	}

	@Override
	public String toString() {
		// return Arrays.toString(elementData);
		StringBuffer res = new StringBuffer("[");
		for (int i = 0; i < top + 1; i++) {
			res.append(elementData[i]);
			if (i != top) {
				res.append(", ");
			}
		}
		res.append("]");
		return res.toString();
	}

}
