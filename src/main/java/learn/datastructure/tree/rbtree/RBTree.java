package learn.datastructure.tree.rbtree;

import java.util.Comparator;

import learn.datastructure.map.MapCustom;

/**
 * 红黑树
 * @author lenovopc
 *
 */
public class RBTree<K, V> {

	// 红黑节点标识
	private static final boolean RED = false;
	private static final boolean BLACK = true;

	private transient Node<K, V> root;

	private final Comparator<? super K> comparator;

	public RBTree() {
		comparator = null;
	}

	/*************对红黑树节点x进行左旋操作 ******************/
	/*
	 *  左旋示意图：对节点x进行左旋
	 *     p                       p
	 *    /                       /
	 *   x                       y
	 *  / \                     / \
	 * lx  y      ----->       x  ry
	 *    / \                 / \
	 *   ly ry               lx ly
	 * 左旋做了三件事：
	 * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
	 * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
	 * 3. 将y的左子节点设为x，将x的父节点设为y
	 */
	private void rolateLeft(Node<K, V> x) {
		if (null != x) {
			Node<K, V> xp = x.parent;
			Node<K, V> y = x.right;
			//1. 将y的左子节点赋给x的右子节点
			x.right = y.left;
			if (y.left != null) { // 并将x赋给y左子节点的父节点(y左子节点非空时)
				y.left.parent = x;
				//2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
				y.parent = xp;
			}
			if (xp == null) {
				this.root = y; //如果x的父节点为空，则将y设为父节点
			} else {
				if (x == xp.left) { //如果x是左子节点
					xp.left = y; //则也将y设为左子节点
				} else {
					xp.right = y; //否则将y设为右子节点
				}
			}
			//3. 将y的左子节点设为x，将x的父节点设为y
			y.left = x;
			x.parent = y;
		}
	}

	/*************对红黑树节点y进行右旋操作 ******************/
	/*
	 * 左旋示意图：对节点y进行右旋
	 *        p                   p
	 *       /                   /
	 *      y                   x
	 *     / \                 / \
	 *    x  ry   ----->      lx  y
	 *   / \                     / \
	 * lx  rx                   rx ry
	 * 右旋做了三件事：
	 * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
	 * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
	 * 3. 将x的右子节点设为y，将y的父节点设为x
	 */
	private void rolateRight(Node<K, V> y) {
		if (null != y) {
			Node<K, V> yp = y.parent;
			Node<K, V> x = y.left;
			// 将x的右子节点赋给y的左子节点
			y.left = x.right;
			if (x.right != null) { //将y赋给x右子节点的父节点(x右子节点非空时)
				x.right.parent = y;
				x.parent = yp;
			}
			if (null == yp) {
				this.root = x;
			} else {
				if (y == y.parent.right) // 如果x是左子节点
					y.parent.right = x; // 则也将y设为左子节点
				else
					y.parent.left = x; // 否则将y设为右子节点
			}
			//3. 将y的左子节点设为x，将x的父节点设为y
			x.right = y;
			y.parent = x;
		}

	}

	static final class Node<K, V> implements MapCustom.Entry<K, V> {
		K key;
		V value;
		Node<K, V> left;
		Node<K, V> right;
		Node<K, V> parent;
		boolean color = BLACK;

		public Node(K key, V value, Node<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V v) {
			V oldValue = value;
			value = v;
			return oldValue;
		}

	}

}
