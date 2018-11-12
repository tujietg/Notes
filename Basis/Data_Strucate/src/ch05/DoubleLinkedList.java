package ch05;

/**
 * @Title DoubleLinkedList.java
 * @description TODO
 * @time 2018��1��26�� ����11:10:21
 * @author �ﲩ��
 * @email tengdowell@gmail.com
 **/
public class DoubleLinkedList {
	private Node first;

	private Node last;

	public DoubleLinkedList() {
		first = null;
		last = null;
	}

	/**
	 * ����һ����㣬��ͷ������в��� ���Ϊ���������²���Ľڵ�Ϊβ�ڵ㣬��������ͷ����ǰһ���ڵ�Ϊ�����ӵĽڵ�
	 * 
	 */
	public void inserthead(int value) {
		Node node = new Node(value);
		if (isEmpty()) {
			last = node;
		} else {
			first.previous = node;
			node.next = first;
		}
		first = node;
	}

	/**
	 * ��β�����в��� ���Ϊ�� ���²���ڵ�Ϊͷ��� ���� β�ڵ����һ���ڵ�Ϊ�²���Ľڵ�
	 */
	public void insertEnd(int value) {
		Node node = new Node(value);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
			node.previous = last;
		}
		last = node;
	}

	/**
	 * ɾ��һ����㣬��ͷ�������ɾ��
	 */
	public Node delete() {
		Node tmp = first;
		if (first.next == null) {
			last = null;
		} else {
			first.next.previous = null;
		}
		first = tmp.next;
		return tmp;
	}

	/**
	 * ɾ���ڵ� ��β������ɾ��
	 */
	public Node deleteend() {
		Node tem = last;
		if (first.next == null) {
			first = null;
		} else {
			last.previous.next = null;
		}
		last = last.previous;
		return tem;
	}

	/**
	 * ��ʾ����
	 */
	public void display() {
		Node current = first;
		while (current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}

	/**
	 * ���ҷ���
	 */
	public Node findNode(int value) {
		Node current = first;
		while (current.data != value) {
			if (current.next == null) {
				return null;
			}
			current = current.next;
		}
		return current;
	}

	/**
	 * ɾ������������������������ɾ��
	 */
	public Node deleteNode(int value) {
		Node current = first;
		Node previous = first;
		while (current.data != value) {
			if (current.next == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}
		previous.next = current.next;
		return current;
	}

	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return first == null;
	}
}
