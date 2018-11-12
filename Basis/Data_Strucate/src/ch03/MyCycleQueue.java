package ch03;

/**
 * 类说明： 循环队列的实现
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月24日 下午2:24:17
 */
public class MyCycleQueue {
	private int[] arr;
	private int elements;
	private int end;
	private int front;

	public MyCycleQueue() {
		arr = new int[10];
		elements = 0;
		front = 0;
		end = -1;
	}

	public MyCycleQueue(int maxlen) {
		arr = new int[maxlen];
		elements = 0;
		front = 0;
		end = -1;
	}

	// 插入数据 从尾部插入
	public void insert(int value) {
		if (end == arr.length - 1) {
			end = -1;
		}
		arr[++end] = value;
		elements++;
	}

	// 删除数据 从头部删除
	public int delete() {
		// 之一front的值 如果直接return返回值 则相差1;
		int value = arr[front++];
		// System.out.println(front);
		if (front == arr.length) {
			front = 0;
		}
		elements--;
		return value;
	}

	// 查看数据 从队列头查看(查看队头第一个数据)
	public int findFront() {
		return arr[front];
	}

	// 判断是否为空
	public boolean isEmpty() {
		return elements == 0;
	}

	// 判断是否满了
	public boolean isFull() {
		return elements == arr.length;
	}
}
