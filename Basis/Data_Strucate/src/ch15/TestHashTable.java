package ch15;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月30日 下午5:09:11
 * @试HashTable
 */
public class TestHashTable {
	public static void main(String[] args) {
		HashTable ht = new HashTable();
		ht.insert(new Info("abc", "张三"));
		ht.insert(new Info("cba", "李四"));
		System.out.println(ht.find("abc").getName());
		System.out.println(ht.find("cba").getName());
	}
}
