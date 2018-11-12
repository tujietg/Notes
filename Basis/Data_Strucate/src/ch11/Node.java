package ch11;

/**
 * @Title Node.java
 * @description 树节点的创建
 * @time 2018年1月27日 下午7:16:39
 * @author 孙博腾
 * @email tengdowell@gmail.com
 **/
public class Node {
	// 数据域（也可以为对象）
	public int data;
	// 数据域
	public String name;
	// 左子节点
	public Node leftchild;
	// 右子节点
	public Node rightchild;

	/**
	 * 构造方法
	 */
	public Node(int data, String name) {
		this.name = name;
		this.data = data;
	}
}
