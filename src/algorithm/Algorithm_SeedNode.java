package algorithm;

import java.util.ArrayList;
import java.util.List;

import data.Node;

public class Algorithm_SeedNode {
	/**
	 * 求解种子节点，并向种子节点中加入对应的种子标签
	 * @param nodes_array 执行前：所有节点列表；执行后：列表中的种子节点已包含种子标签
	 * @param link_matrix 节点关联矩阵
	 * @param seed_label 种子标签名称列表
	 * @param x 平滑因子，暂时建议大于0.5；选取种子节点时，x越大则节点度数越重要，x越小则种子标签在原始标签中出现的次数越重要
	 * @return 种子节点在 nodes_array 中的对应下标列表
	 */
	public int[] execute(Node[] nodes_array/*in/out*/, int[][] link_matrix/*in*/,String[] seed_label/*in*/,double x){
		int K = seed_label.length;//种子标签数量
		int[] seed_node = new int[K];//最终需要返回的种子节点列表
		int nodeSize = link_matrix.length;//节点的数量
		
		for(int i=0;i<K;i++){
			double[] suit = new double[nodeSize];//为所有节点建立针对第i个种子标签的suit值列表
			int maxSuitIndex = 0;
			//遍历所有节点，针对每个节点j计算suit值
			for(int j=0;j<nodeSize;j++){
				double degree = (double)countDegree(j,link_matrix);//得到节点j的度
				double appear = (double)nodes_array[j].label_list.get(seed_label[i]);//标签i在节点j的原始标签中出现的次数
				
				suit[j] = degree*x + appear*(1-x);//计算节点j的suit
				//记录最大suit值的下标
				if(suit[j] >= suit[maxSuitIndex]){
					maxSuitIndex = j;
				}
			}
			
			//对于下标为 maxSuitIndex 的节点，清空标签列表，赋予种子标签i
		}
		
		return seed_node;
	}
	
	/**
	 * 计算指定节点的度
	 * @param nodeIndex 指定节点在矩阵的下标
	 * @param link_matrix 关联矩阵
	 * @return 该节点的度
	 */
	private int countDegree(int nodeIndex, int[][] link_matrix){
		int result = 0;//所求节点的度
		int nodeSize = link_matrix.length;
		//遍历矩阵的第nodeIndex行，计算1的数量
		for(int i=0;i<nodeSize;i++){
			if(link_matrix[nodeIndex][i] == 1){
				result++;
			}
		}
		return result;
	}
}
