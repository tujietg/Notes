package ch18;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��31�� ����10:14:05
 * @��˵��:
 */
public class TestGraph {
	public static void main(String[] args) {
		Graph g = new Graph();
		// ���붥��
		g.insertV('A');
		g.insertV('B');
		g.insertV('C');
		// �����
		g.insertB(0, 1);
		g.insertB(0, 2);
		g.insertB(1, 1);
	}
}
