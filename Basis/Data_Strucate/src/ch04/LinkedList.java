package ch04;

/**
 * @Title LinkedList.java
 * @description TODO
 * @time 2018年1月26日 下午10:33:05
 * @author 孙博腾
 * @email tengdowell@gmail.com
 **/
public class LinkedList {
	private Node first;

	public LinkedList() {
		first = null;
	}

	public void insert(int value) {
		Node node = new Node(value);
		node.next = first;
		first = node;
	}

	public Node delete() {
		Node tmp = first;
		first = tmp.next;
		return tmp;
	}

	public void display() {
		Node current = first;
		while (current != null) {
			current.display();
			current = current.next;
		}
	}

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
}
