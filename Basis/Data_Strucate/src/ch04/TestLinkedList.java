package ch04;

/**
 * @Title TestLinkedList.java
 * @description TODO
 * @time 2018��1��26�� ����10:33:28
 * @author �ﲩ��
 * @email tengdowell@gmail.com
 **/
public class TestLinkedList {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.insert(10);
		ll.insert(11);
		ll.insert(0);
		ll.insert(1);
		// ll.delete();
		ll.display();
		System.out.println("***********************");
		System.out.println(ll.findNode(11).data);
		System.out.println("***********************");
		System.out.println(ll.deleteNode(10).data);
	}
}
