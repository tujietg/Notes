package ch18;

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

}
