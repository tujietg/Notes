package ch06;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��30�� ����3:01:33
 * @��˵��:��������
 */
public class Triangle {
	// �ݹ�ִ��
	public static int tri(int num) {
		if (num == 1) {
			return 1;
		} else {
			return tri(num - 1) + num;
		}
	}

	// ѭ�����
	public static int useWhile(int num) {
		int res = 0;
		while (num > 0) {
			res = res + num;
			--num;
		}
		return res;
	}
}
