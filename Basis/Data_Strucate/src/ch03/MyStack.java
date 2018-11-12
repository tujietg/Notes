package ch03;

/**
 * 绫昏鏄庯細 鏍堢殑瀹炵幇 锛堝熀浜庢暟缁勶級
 * 
 * @author 瀛欏崥鑵� E-mail: tengdowell@gmail.com
 * @version 鍒涘缓鏃堕棿锛�2018骞�1鏈�24鏃� 涓婂崍11:23:05
 */
public class MyStack {
	private int[] arr;
	// 鏁扮粍鐨勪笅鏍�
	private int top;

	public MyStack() {
		arr = new int[10];
		top = -1;
	}

	public MyStack(int maxlen) {
		arr = new int[maxlen];
		top = -1;
	}

	// 澧炲姞鏁版嵁鏂规硶
	public void push(int value) {
		arr[++top] = value;
	}

	// 鍒犻櫎鏁版嵁
	public int pop() {
		return arr[top--];
	}

	// 鍒ゆ柇鏄惁涓虹┖
	public boolean isEmpty() {
		return top < 0;
	}

	// 鏌ョ湅top鐨勫��
	public int findlast() {
		return arr[top];
	}

	public long peek() {
		return arr[top];
	}

	// 鍒ゆ柇鏄惁婊′簡
	public boolean isFull() {
		return top == arr.length - 1;
	}
}
