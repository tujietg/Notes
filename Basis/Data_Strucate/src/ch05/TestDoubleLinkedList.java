package ch05;

/**
 * @Title TestDoubleLinkedList.java
 * @description 测试双向链表
 * @time 2018年1月27日 下午5:48:45
 * @author 孙博腾
 * @email tengdowell@gmail.com
 **/
public class TestDoubleLinkedList {
	public static void main(String[] args) {
		DoubleLinkedList dl = new DoubleLinkedList();
		dl.inserthead(10);
		dl.inserthead(11);
		dl.inserthead(12);
		// dl.insertEnd(10);
		// dl.insertEnd(11);
		// dl.insertEnd(12);
		dl.display();
		while (!dl.isEmpty()) {
			dl.delete();
			dl.display();
		}
	}
}
