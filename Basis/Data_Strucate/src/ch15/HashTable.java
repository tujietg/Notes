package ch15;

import java.math.BigInteger;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月30日 下午4:59:18
 * @类说明:hashtable 表格
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
	 * 插入数据
	 */
	public void insert(Info info) {
		arr[hashcode(info.getKey())] = info;
	}

	// 字符串为key值时候转换为askII码相加

	// public int hashcode(String key) {
	// int res = 0;
	// for (int i = key.length() - 1; i >= 0; i--) {
	// int letter = key.charAt(i) - 96;
	// res += letter;
	// }
	// return res;
	// }
	// 为了防止重复，则采用幂的连乘
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
	// 数值太大的时候res不满足，所以需要转换为BigInteger
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
	 * 查找数据
	 */
	public Info find(String key) {
		return arr[hashcode(key)];
	}
}
