package ch05;

import ch05.Node;

/**
 * @Title Node.java
 * @description TODO
 * @time 2018��1��26�� ����10:38:59
 * @author �ﲩ��
 * @email tengdowell@gmail.com
 **/
public class Node {
	public int data;
	public Node next;
	public Node previous;

	public Node(int value) {
		this.data = value;
	}

	public void display() {
		System.out.print(data + " ");
	}
}
