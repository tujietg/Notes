package ch02;

/**
 * 类说明：
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月23日 下午3:02:47
 */
public class TestSort {
	public static void main(String[] args) {
		int[] arr1 = { 1, 5, 10, 3, 2 };
		InsertSort.insertSort(arr1);
		System.out.print("[");
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("]");

	}
}
