package ch05;

/**
 * @Title TestFirstLastLinkedList.java
 * @description TODO
 * @time 2018��1��26�� ����11:04:53
 * @author �ﲩ��
 * @email tengdowell@gmail.com
 **/
public class TestFirstLastLinkedList {
	public static void main(String[] args) {
		FirstLastLinkedList f = new FirstLastLinkedList();
		// f.insertEnd(10);
		// f.insertEnd(11);
		// f.insertEnd(12);
		f.inserthead(10);
		f.inserthead(11);
		f.inserthead(12);
		f.display();
	}
}
