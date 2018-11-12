package ch20;

import ch03.MyStack;

/**
 * @author:�ﲩ��
 * @E-mail:tengdowell@gmail.com
 * @version ����ʱ�䣺2018��1��31�� ����9:47:25
 * @��˵��:ͼ��ʵ��
 */
public class Graph {
	// ���������
	private Vertex[] vertexList;
	// �ڽӾ���
	private int[][] adjMat;
	// ����������Ŀ
	private int maxLen;
	// ��ǰ����(�൱����Чֵ)
	private int nVertex;
	// ����stack
	private MyStack stack;

	// ���췽��
	public Graph() {
		maxLen = 10;
		vertexList = new Vertex[maxLen];
		adjMat = new int[maxLen][maxLen];
		for (int i = 0; i < maxLen; i++) {
			for (int j = 0; j < maxLen; j++) {
				adjMat[i][j] = 0;
			}
		}
		nVertex = 0;
		stack = new MyStack();
	}

	// ���붥��
	public void insertV(char label) {
		vertexList[nVertex++] = new Vertex(label);
	}

	// ���ӱ�(���ڽӾ���ʵ��)
	public void insertB(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}

	// �ҳ�û�б����ʵĶ���
	public int getadjUnvisitedVertex(int v) {
		for (int i = 0; i < nVertex; i++) {
			if (adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
				return i;
			}
		}
		return -1;
	}

	// dfs������ȱ���
	public void dfs() {
		vertexList[0].wasVisited = true;
		// ��ʾ����
		display(0);
		// ѹ��stack��
		stack.push(0);
		while (!stack.isEmpty()) {
			// �ҳ���һ������û�����ʵĶ���
			int x = getadjUnvisitedVertex((int) stack.peek());
			if (x == -1) {
				stack.pop();
			} else {
				vertexList[x].wasVisited = true;
				display(x);
				stack.push(x);
			}
		}
		for (int i = 0; i < nVertex; i++) {
			vertexList[i].wasVisited = false;
		}
	}

	// ͼ������С�Ķ����� �������ȶ�������һ��������������������
	public void setTree() {
		vertexList[0].wasVisited = true;
		// ��ʾ����
		// display(0);
		// ѹ��stack��
		stack.push(0);
		while (!stack.isEmpty()) {
			// ȡ����ǰ�ڵ�
			int currentVertex = (int) stack.peek();
			// �ҳ���һ������û�����ʵĶ���
			int x = getadjUnvisitedVertex(currentVertex);
			if (x == -1) {
				stack.pop();
			} else {
				vertexList[x].wasVisited = true;
				stack.push(x);
				display(currentVertex);
				System.out.print("-");
				display(x);
				System.out.print(" ");

			}
		}
		for (int i = 0; i < nVertex; i++) {
			vertexList[i].wasVisited = false;
		}
	}

	// BFS�����������
	public void bfs() {
		// ͨ������ʵ��
	}

	public void display(int v) {
		System.out.print(vertexList[v].value);
	}

}
