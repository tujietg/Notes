package ch13;

/**
 * @Title TestTree.java
 * @description TODO
 * @time 2018年1月27日 下午8:04:28
 * @author 孙博腾
 * @email tengdowell@gmail.com
 **/
public class TestTree {
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(10, "mms");
		tree.insert(112, "dd");
		tree.insert(8, "aa");
		tree.insert(3, "ll");
		tree.insert(2, "22");
		tree.insert(9, "92");
		tree.insert(11, "22222");
		// System.out.println(tree.root.data);
		// System.out.println(tree.root.rightchild.data);
		// System.out.println(tree.root.leftchild.leftchild.data);
		// System.out.println(tree.root.leftchild.data);
		// System.out.print(tree.find(10).data + " " + tree.find(10).name);
		// tree.delete(2);
		// tree.delete(11);
		// tree.delete(3);
		// tree.delete(112);
		tree.endOrder(tree.root);
	}
}
