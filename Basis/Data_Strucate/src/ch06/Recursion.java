package ch06;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��30�� ����2:57:08
 * @��˵��:
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
