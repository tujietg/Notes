package ch01;

/**
 * 类说明：测试MyArray
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月23日 上午10:33:49
 */
public class TextMyArray {
	public static void main(String[] args) {
		MyArray my = new MyArray();
		my.insert(10);
		my.insert(12);
		my.insert(11);
		my.insert(14);
		my.insert(19);
		// my.deleteIndex(0);
		my.display();
		// my.updata(0, 10);
		// my.display();
		System.out.println(my.findValue(0));
		System.out.println(my.findIndex(1));

	}
}
