package ch07;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��30�� ����4:01:41
 * @��˵��: ��ŵ������ from:��ʼ inner:�м�� to:����
 */
public class HannoTower {
	public static void ht(int num, char from, char inner, char to) {
		if (num == 1) {
			System.out.println("����1 ��" + from + "�ƶ���" + to);
		} else {
			ht(num - 1, from, to, inner);
			System.out.println("����" + num + "��" + from + "�ƶ���" + to);
			ht(num - 1, inner, from, to);
		}
	}
}
