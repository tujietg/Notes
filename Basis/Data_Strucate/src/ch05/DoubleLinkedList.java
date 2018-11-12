package ch05;

/**
 * @Title DoubleLinkedList.java
 * @description TODO
 * @time 2018年1月26日 下午11:10:21
 * @author 孙博腾
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
	 * 插入一个结点，在头结点后进行插入 如果为空则设置新插入的节点为尾节点，否则设置头结点的前一个节点为新增加的节点
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
	 * 从尾部进行插入 如果为空 则新插入节点为头结点 否则 尾节点的下一个节点为新插入的节点
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
	 * 删除一个结点，在头结点后进行删除
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
	 * 删除节点 从尾部进行删除
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
	 * 显示方法
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
