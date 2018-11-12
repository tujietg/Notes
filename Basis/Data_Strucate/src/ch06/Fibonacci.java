package ch06;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月30日 下午3:16:37
 * @类说明:Fibonacci 规律为test(n) = test(n-1)+test(n-2);test(1) = 0;test(2) = 1
 */
public class Fibonacci {
	public static int fi(int n) {
		if (n == 1) {
			return 0;
		} else if (n == 2) {
			return 1;
		} else {
			return fi(n - 1) + fi(n - 2);
		}

	}
}
