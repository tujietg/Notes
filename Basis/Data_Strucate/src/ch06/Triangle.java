package ch06;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月30日 下午3:01:33
 * @类说明:特殊三角
 */
public class Triangle {
	// 递归执行
	public static int tri(int num) {
		if (num == 1) {
			return 1;
		} else {
			return tri(num - 1) + num;
		}
	}

	// 循环完成
	public static int useWhile(int num) {
		int res = 0;
		while (num > 0) {
			res = res + num;
			--num;
		}
		return res;
	}
}
