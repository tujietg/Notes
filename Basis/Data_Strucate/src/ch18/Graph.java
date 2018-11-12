package ch18;

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

}
