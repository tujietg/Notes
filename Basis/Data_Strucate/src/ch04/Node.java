package ch04;

/**
 * @Title Node.java
 * @description TODO
 * @time 2018年1月26日 下午10:32:28
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
		System.out.println(data + " ");
	}
}
