package ch03;

/**
 * 类说明：
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月24日 上午11:43:53
 */
public class TestMyStack {
	public static void main(String[] args) {
		MyStack ms = new MyStack();
		ms.push(0);
		ms.push(1);
		ms.push(2);
		ms.push(3);
		ms.push(4);
		ms.push(5);
		ms.push(6);
		ms.push(7);
		ms.push(8);
		ms.push(9);

		System.out.println(ms.findlast());
		// System.out.println(ms.pop());
		System.out.println(ms.findlast());
		// System.out.println(ms.isEmpty());
		System.out.println(ms.isFull());
	}
}
