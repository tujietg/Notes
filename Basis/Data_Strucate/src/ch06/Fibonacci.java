package ch06;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��30�� ����3:16:37
 * @��˵��:Fibonacci ����Ϊtest(n) = test(n-1)+test(n-2);test(1) = 0;test(2) = 1
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
