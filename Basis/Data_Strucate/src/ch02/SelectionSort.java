package ch02;

/**
 * 类说明： 选择排序 每次遍历找出最小的数据放入最前端。
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月23日 下午3:22:52
 */
public class SelectionSort {
	// [10,6,7,2,3,11]
	public static void selectionSort(int[] arr) {
		int k = 0;
		int tem = 0;
		for (int i = 0; i < arr.length; i++) {
			k = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[k] > arr[j]) {
					k = j;
				}
			}
			tem = arr[i];
			arr[i] = arr[k];
			arr[k] = tem;
		}
	}
}
