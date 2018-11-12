package ch01;

/**
 * 类说明： 順序排序的數組
 * 
 * @author 孙博腾 E-mail: tengdowell@gmail.com
 * @version 创建时间：2018年1月23日 上午10:43:10
 */
public class MyOrderArray {
	private int[] arr;
	// 有效个数
	private int elements;

	public MyOrderArray() {
		arr = new int[50];
	}

	public MyOrderArray(int maxlength) {
		arr = new int[maxlength];
	}

	// 增加数据(按照順序排序)
	public void insert(int value) {
		int i;
		for (i = 0; i < elements; i++) {
			if (arr[i] > value) {// 影响着正序 还是倒叙
				break;
			}
		}
		for (int j = elements; j > i; j--) {
			arr[j] = arr[j - 1];
		}
		arr[i] = value;
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

	// 查找方法 (1 按照value查找index)二分法
	public int findIndex(int value) {
		int mid = 0;
		int low = 0;
		int top = elements;
		while (true) {
			mid = (low + top) / 2;
			if (arr[mid] == value) {
				return mid;
			} else if (arr[mid] > value) {
				top = mid - 1;
			} else {
				low = mid + 1;
			}
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
