package ch08;

/**
 * 类说明：ShellSort 基于插入排序 多了 间隔计算： h = 3*h +1 间隔减少： h = (h-1)/3
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月24日 上午8:11:24
 */
public class ShellSort {
	// { 1, 5, 10, 3, 2, 12, 33, 1, 323, 0 };
	// 0 1 2 3 4 5 6 7 8 9
	public static void shellSort(int[] arr) {
		int h = 1;
		int tmp = 0;
		while (h < arr.length) {
			h = h * 3 + 1;
		}
		h = (h - 1) / 3;
		while (h > 0) {
			// 插入排序
			for (int i = h; i < arr.length; i = i + h) {
				tmp = arr[i];
				int j = i - h;
				while (j >= 0 && arr[j] > tmp) {
					arr[j + h] = arr[j];
					j = j - h;
				}
				arr[j + h] = tmp;
			}
			h = (h - 1) / 3;
		}
	}
}
