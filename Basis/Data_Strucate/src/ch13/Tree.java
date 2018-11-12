package ch13;

/**
 * @Title Tree.javas
 * @description 未完，待续~
 * @time 2018年1月27日 下午7:19:54
 * @author 孙博腾
 * @email tengdowell@gmail.com
 **/
public class Tree {
	// 根节点的创建
	public Node root;

	/**
	 * 插入节点
	 */
	public void insert(int value, String name) {
		Node newNode = new Node(value, name);
		// 当前节点
		Node current = root;
		// 父节点
		Node parent;
		if (current == null) {
			root = newNode;
			return;
		} else {
			while (true) {
				parent = current;
				if (value < current.data) {
					current = current.leftchild;
					if (current == null) {
						parent.leftchild = newNode;
						break;
					}
				} else {
					current = current.rightchild;
					if (current == null) {
						parent.rightchild = newNode;
						break;
					}
				}
			}
		}
	}

	/**
	 * 删除节点：分为三种情况，第一种，删除的为叶子节点，第二种，删除节点下有一个子节点，第三种，删除节点下有两个节点
	 */
	public boolean delete(int value) {
		Node current = root;
		if (root.leftchild == null && root.rightchild == null) {
			root = null;
		}
		Node parent = current;
		boolean isleftchild = true;
		while (current.data != value) {
			parent = current;
			if (current.data < value) {
				current = current.rightchild;
				isleftchild = false;
			} else {
				current = current.leftchild;
				isleftchild = true;
			}
			if (current == null) {
				return true;
			}
		}
		// 第一种情况
		if (current.leftchild == null && current.rightchild == null) {
			if (isleftchild) {
				parent.leftchild = null;
			} else {
				parent.rightchild = null;
			}
			// 第二种情况
		} else if (current.rightchild == null) {
			if (isleftchild) {
				parent.leftchild = current.leftchild;
			} else {
				parent.rightchild = current.leftchild;
			}
		} else {
			if (isleftchild) {
				parent.leftchild = current.rightchild;
			} else {
				parent.rightchild = current.leftchild;
			}
		}
		return true;
	}

	/**
	 * 查找节点
	 */
	public Node find(int value) {
		Node current = root;
		while (current.data != value) {
			if (current.data < value) {
				current = current.rightchild;
			} else {
				current = current.leftchild;
			}
			if (current == null) {
				return null;
			}
		}
		return current;
	}

	/**
	 * 前序遍历（先访问节点，然后遍历左子树，再次遍历右子树）
	 */
	public void frontOrder(Node localNode) {
		if (localNode != null) {
			// 访问节点
			System.out.println("数据" + localNode.data + "姓名" + localNode.name);
			// 遍历左子树
			frontOrder(localNode.leftchild);
			// 遍历右子树
			frontOrder(localNode.rightchild);
		}
	}

	/**
	 * 中序遍历（先遍历左子树，在访问节点，在遍历右子树） 最终结果按照数据从小到大排序
	 */
	public void cenOrder(Node localNode) {
		if (localNode != null) {
			// 遍历左子树
			cenOrder(localNode.leftchild);
			// 访问节点
			System.out.println("数据" + localNode.data + "姓名" + localNode.name);
			// 遍历右子树
			cenOrder(localNode.rightchild);
		}
	}

	/**
	 * 后序遍历（先遍历左子树，在遍历右子树，在访问节点）
	 */
	public void endOrder(Node localNode) {
		if (localNode != null) {
			// 遍历左子树
			endOrder(localNode.leftchild);
			// 遍历左右树
			endOrder(localNode.rightchild);
			// 访问节点
			System.out.println("数据" + localNode.data + "姓名" + localNode.name);
		}
	}
}
