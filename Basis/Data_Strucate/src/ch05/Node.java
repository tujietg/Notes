package ch05;

import ch05.Node;

/**
 * @Title Node.java
 * @description TODO
 * @time 2018年1月26日 下午10:38:59
 * @author 孙博腾
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
