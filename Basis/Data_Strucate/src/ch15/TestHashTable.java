package ch15;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��30�� ����5:09:11
 * @��HashTable
 */
public class TestHashTable {
	public static void main(String[] args) {
		HashTable ht = new HashTable();
		ht.insert(new Info("abc", "����"));
		ht.insert(new Info("cba", "����"));
		System.out.println(ht.find("abc").getName());
		System.out.println(ht.find("cba").getName());
	}
}
