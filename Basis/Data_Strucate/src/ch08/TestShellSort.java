package ch08;

import ch02.InsertSort;

/**
 * 类说明：
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月24日 上午8:38:02
 */
public class TestShellSort {
	public static void main(String[] args) {
		int[] arr1 = { 1, 5, 10, 3, 2, 12, 33, 1, 323, 0 };
		// 0 1 2 3 4 5 6 7 8 9
		ShellSort.shellSort(arr1);
		System.out.print("[");
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.print("]");

	}
}
