package ch05;

/**
 * @Title LinkedList.java
 * @description TODO
 * @time 2018年1月26日 下午10:33:05
 * @author 孙博腾
 * @email tengdowell@gmail.com
 **/
public class FirstLastLinkedList {
	private Node first;
	private Node last;

	public FirstLastLinkedList() {
		first = null;
		last = null;
	}

	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void inserthead(int value) {
		Node node = new Node(value);
		if (isEmpty()) {
			last = node;
		}
		node.next = first;
		first = node;
	}

	/**
	 * 从尾部进行插入 如果为空 则新插入节点为头结点 否则 尾节点的下一个节点为新插入的节点
	 */
	public void insertEnd(int value) {
		Node node = new Node(value);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
	}

	/**
	 * 删除一个结点，在头结点后进行删除
	 */
	public Node delete() {
		Node tmp = first;
		if (first.next == null) {
			last = null;
		}
		first = tmp.next;
		return tmp;
	}

	/**
	 * 显示方法
	 */
	public void display() {
		Node current = first;
		while (current != null) {
			current.display();
			current = current.next;
		}
	}

	/**
	 * 查找方法
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
	 * 删除方法，根据数据域来进行删除
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
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return first == null;
	}
}
