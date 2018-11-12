package ch20;

import ch03.MyStack;

/**
 * @author:孙博腾
 * @E-mail:tengdowell@gmail.com
 * @version 创建时间：2018年1月31日 上午9:47:25
 * @类说明:图的实现
 */
public class Graph {
	// 顶点的数组
	private Vertex[] vertexList;
	// 邻接矩阵
	private int[][] adjMat;
	// 顶点的最大数目
	private int maxLen;
	// 当前顶点(相当于有效值)
	private int nVertex;
	// 创建stack
	private MyStack stack;

	// 构造方法
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

	// 插入顶点
	public void insertV(char label) {
		vertexList[nVertex++] = new Vertex(label);
	}

	// 增加边(在邻接矩阵实现)
	public void insertB(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}

	// 找出没有被访问的顶点
	public int getadjUnvisitedVertex(int v) {
		for (int i = 0; i < nVertex; i++) {
			if (adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
				return i;
			}
		}
		return -1;
	}

	// dfs深度优先遍历
	public void dfs() {
		vertexList[0].wasVisited = true;
		// 显示顶点
		display(0);
		// 压入stack中
		stack.push(0);
		while (!stack.isEmpty()) {
			// 找出下一个可以没被访问的顶点
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

	// 图生成最小的二叉树 （边数比顶点数少一）深度优先搜索代码改正
	public void setTree() {
		vertexList[0].wasVisited = true;
		// 显示顶点
		// display(0);
		// 压入stack中
		stack.push(0);
		while (!stack.isEmpty()) {
			// 取到当前节点
			int currentVertex = (int) stack.peek();
			// 找出下一个可以没被访问的顶点
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

	// BFS广度优先搜索
	public void bfs() {
		// 通过对列实现
	}

	public void display(int v) {
		System.out.print(vertexList[v].value);
	}

}
