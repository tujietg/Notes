package ch07;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月30日 下午4:01:41
 * @类说明: 汉诺塔问题 from:开始 inner:中间的 to:到达
 */
public class HannoTower {
	public static void ht(int num, char from, char inner, char to) {
		if (num == 1) {
			System.out.println("盘子1 從" + from + "移动到" + to);
		} else {
			ht(num - 1, from, to, inner);
			System.out.println("盘子" + num + "从" + from + "移动到" + to);
			ht(num - 1, inner, from, to);
		}
	}
}
