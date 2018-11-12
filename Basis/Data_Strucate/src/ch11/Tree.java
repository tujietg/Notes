package ch11;

/**
 * @Title Tree.javas
 * @description
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
						return;
					}
				} else {
					current = current.rightchild;
					if (current == null) {
						parent.rightchild = newNode;
						return;
					}
				}
			}
		}
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
}
