package ch06;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月30日 下午2:57:08
 * @类说明:
 */
public class Recursion {
	public static void main(String[] args) {
		test(100);
	}

	public static void test(int num) {
		System.out.println(num);
		if (num == 0) {
			return;
		}
		num--;
		test(num);
	}
}
