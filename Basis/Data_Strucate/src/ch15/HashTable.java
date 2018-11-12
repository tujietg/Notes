package ch15;

import java.math.BigInteger;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��30�� ����4:59:18
 * @��˵��:hashtable ���
 */
public class HashTable {
	private Info[] arr;

	public HashTable() {
		arr = new Info[10000];
	}

	public HashTable(int maxlen) {
		arr = new Info[maxlen];
	}

	/**
	 * ��������
	 */
	public void insert(Info info) {
		arr[hashcode(info.getKey())] = info;
	}

	// �ַ���Ϊkeyֵʱ��ת��ΪaskII�����

	// public int hashcode(String key) {
	// int res = 0;
	// for (int i = key.length() - 1; i >= 0; i--) {
	// int letter = key.charAt(i) - 96;
	// res += letter;
	// }
	// return res;
	// }
	// Ϊ�˷�ֹ�ظ���������ݵ�����
	// int res = 0;
	// int d27 = 1;
	//
	// public int hashcode(String key) {
	// for (int i = key.length() - 1; i >= 0; i--) {
	// int letter = key.charAt(i) - 96;
	// res += letter * d27;
	// d27 *= 27;
	// }
	// return res;
	// }
	// ��ֵ̫���ʱ��res�����㣬������Ҫת��ΪBigInteger
	public int hashcode(String key) {
		BigInteger hashVal = new BigInteger("0");
		BigInteger pow27 = new BigInteger("1");
		for (int i = key.length() - 1; i >= 0; i--) {
			int letter = key.charAt(i) - 96;
			BigInteger letterB = new BigInteger(String.valueOf(letter));
			hashVal = hashVal.add(letterB.multiply(pow27));
			pow27 = pow27.multiply(new BigInteger(String.valueOf(27)));
		}
		return hashVal.mod(new BigInteger(String.valueOf(arr.length))).intValue();
	}

	/**
	 * ��������
	 */
	public Info find(String key) {
		return arr[hashcode(key)];
	}
}
