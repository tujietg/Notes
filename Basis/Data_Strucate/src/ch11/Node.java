package ch11;

/**
 * @Title Node.java
 * @description ���ڵ�Ĵ���
 * @time 2018��1��27�� ����7:16:39
 * @author �ﲩ��
 * @email tengdowell@gmail.com
 **/
public class Node {
	// ������Ҳ����Ϊ����
	public int data;
	// ������
	public String name;
	// ���ӽڵ�
	public Node leftchild;
	// ���ӽڵ�
	public Node rightchild;

	/**
	 * ���췽��
	 */
	public Node(int data, String name) {
		this.name = name;
		this.data = data;
	}
}
