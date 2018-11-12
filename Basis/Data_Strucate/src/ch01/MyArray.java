package ch01;

/**
 * 类说明：自实现数组的功能,增删改查 ！
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月23日 上午9:32:22
 */
public class MyArray {
	private int[] arr;
	// 有效个数
	private int elements;

	public MyArray() {
		arr = new int[50];
	}

	public MyArray(int maxlength) {
		arr = new int[maxlength];
	}

	// 增加数据
	public void insert(int value) {
		arr[elements] = value;
		elements++;
	}

	// 删除数据(1 按照下标删除)
	public void deleteIndex(int index) {
		if (index > elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		for (int i = index; i < elements; i++) {
			arr[i] = arr[i + 1];
		}
		elements--;
	}

	// 删除数据(2 按照数据删除)
	public void deleteData(int value) {
		MyArray my = new MyArray();
		int index = my.findIndex(value);
		if (index > elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		for (int i = index; i < elements; i++) {
			arr[i] = arr[i + 1];
		}
		elements--;
	}

	// 查找方法 (1 按照value查找index)
	public int findIndex(int value) {
		int i;
		for (i = 0; i < elements; i++) {
			if (value == arr[i]) {
				break;
			}
		}
		if (i == elements) {
			return -1;
		} else {
			return i;
		}

	}

	// 根据索引来查找2
	public int findValue(int index) {
		if (index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return arr[index];
		}
	}

	// 更新数据
	public void updata(int index, int value) {
		if (index < 0 || index >= elements) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			arr[index] = value;
		}
	}

	// 显示数据
	public void display() {
		System.out.print("[");
		for (int i = 0; i < elements; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("]");
	}
}
