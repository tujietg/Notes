package ch20;

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
		g.insertV('D');
		g.insertV('E');
		// �����
		g.insertB(0, 1);
		g.insertB(0, 2);
		g.insertB(0, 3);
		g.insertB(0, 4);
		g.insertB(1, 2);
		g.insertB(1, 3);
		g.insertB(1, 4);
		g.insertB(2, 3);
		g.insertB(2, 4);
		g.insertB(3, 4);

		// g.dfs();
		g.setTree();
	}
}
