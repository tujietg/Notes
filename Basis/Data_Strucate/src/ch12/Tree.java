package ch12;

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

	/**
	 * ǰ��������ȷ��ʽڵ㣬Ȼ��������������ٴα�����������
	 */
	public void frontOrder(Node localNode) {
		if (localNode != null) {
			// ���ʽڵ�
			System.out.println("����" + localNode.data + "����" + localNode.name);
			// ����������
			frontOrder(localNode.leftchild);
			// ����������
			frontOrder(localNode.rightchild);
		}
	}

	/**
	 * ����������ȱ������������ڷ��ʽڵ㣬�ڱ����������� ���ս���������ݴ�С��������
	 */
	public void cenOrder(Node localNode) {
		if (localNode != null) {
			// ����������
			cenOrder(localNode.leftchild);
			// ���ʽڵ�
			System.out.println("����" + localNode.data + "����" + localNode.name);
			// ����������
			cenOrder(localNode.rightchild);
		}
	}

	/**
	 * ����������ȱ������������ڱ������������ڷ��ʽڵ㣩
	 */
	public void endOrder(Node localNode) {
		if (localNode != null) {
			// ����������
			endOrder(localNode.leftchild);
			// ����������
			endOrder(localNode.rightchild);
			// ���ʽڵ�
			System.out.println("����" + localNode.data + "����" + localNode.name);
		}
	}
}
