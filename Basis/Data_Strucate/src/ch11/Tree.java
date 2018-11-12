package ch11;

/**
 * @Title Tree.javas
 * @description
 * @time 2018��1��27�� ����7:19:54
 * @author �ﲩ��
 * @email tengdowell@gmail.com
 **/
public class Tree {
	// ���ڵ�Ĵ���
	public Node root;

	/**
	 * ����ڵ�
	 */
	public void insert(int value, String name) {
		Node newNode = new Node(value, name);
		// ��ǰ�ڵ�
		Node current = root;
		// ���ڵ�
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
	 * ���ҽڵ�
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
