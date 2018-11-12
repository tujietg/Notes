package ch03;

/**
 * 类说明：
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月24日 下午1:11:36
 */
public class TestMyQueue {
	public static void main(String[] args) {
		MyCycleQueue mq = new MyCycleQueue(4);
		mq.insert(11);
		mq.insert(12);
		mq.insert(13);
		mq.insert(14);
		for (int i = 0; i < 4; i++) {
			System.out.println(mq.findFront());
			mq.delete();
		}
		// while (!mq.isEmpty()) {
		// System.out.print(mq.delete() + " ");
		// }
		// System.out.println();
		System.out.println(mq.isEmpty());
		mq.insert(21);
		mq.insert(22);
		mq.insert(23);
		for (int i = 0; i < 4; i++) {
			System.out.println(mq.findFront());
			mq.delete();
		}
		System.out.println(mq.isEmpty());

	}
}
