package ch02;

/**
 * 类说明：insertSort
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月23日 下午3:59:21
 */
public class InsertSort {
	// [10,6,7,2,3,11]
	public static void insertSort(int[] arr) {
		int tmp = 0;
		for (int i = 1; i < arr.length; i++) {
			tmp = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > tmp) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = tmp;
		}
	}
}
