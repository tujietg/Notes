package ch02;

/**
 * 类说明：冒牌排序
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月23日 下午2:49:40
 */
public class BubbleSort {
	// [10,6,7,2,3,11]
	// 把最小的數字冒泡到最前面
	public static void bubbleSort(int[] arr) {
		int tem = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = arr.length - 1; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					tem = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = tem;
				}
			}
		}
	}

	// 每次把最大的值放在最后
	// [10,6,7,2,3,11]
	public static void bubbleSort2(int[] arr) {
		int tem = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					tem = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tem;
				}
			}
		}
	}
}
