package ch03;

/**
 * 类说明：
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月24日 下午1:00:42
 */
public class MyQueue {
	private int[] arr;
	private int elements;
	private int end;
	private int front;

	public MyQueue() {
		arr = new int[10];
		elements = 0;
		front = 0;
		end = -1;
	}

	public MyQueue(int maxlen) {
		arr = new int[maxlen];
		elements = 0;
		front = 0;
		end = -1;
	}

	// 插入数据 从尾部插入
	public void insert(int value) {
		arr[++end] = value;
		elements++;
	}

	// 删除数据 从头部删除
	public int delete() {
		elements--;
		return arr[front++];
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
