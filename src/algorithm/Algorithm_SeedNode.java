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
	public List<Integer> execute(Node[] nodes_array/*in/out*/, int[][] link_matrix/*in*/,String[] seed_label/*in*/,double x){
		int K = seed_label.length;//种子标签数量
		List<Integer> seed_node = new ArrayList<Integer>();//最终需要返回的种子节点列表
		int nodeSize = link_matrix.length;//节点的数量
		
		//对于每个种子标签，计算suit最大的节点
		for(int i=0;i<K;i++){
			double[] suit = new double[nodeSize];//为所有节点建立针对第i个种子标签的suit值列表
			int maxSuitIndex = 0;
			//遍历所有节点，针对每个节点j计算suit值
			for(int j=0;j<nodeSize;j++){
				double degree = (double)nodes_array[j].degree;//得到节点j的度
				double appear = (double)nodes_array[j].label_list.get(seed_label[i]);//标签i在节点j的原始标签中出现的次数
				
				suit[j] = degree*x + appear*(1-x);//计算节点j的suit
				//记录最大suit值的下标
				if(suit[j] >= suit[maxSuitIndex]){//当前允许同一个节点被选为多个标签的种子，如果需要改为种子节点不重叠，则添加判断该节点是否在seed_node列表中
					maxSuitIndex = j;
				}
			}
			
			//对于下标为 maxSuitIndex 的节点，清空标签列表，赋予种子标签i
			nodes_array[maxSuitIndex].label_list.clear();
			nodes_array[maxSuitIndex].addLabel(seed_label[i]);
			seed_node.add(maxSuitIndex);//将该节点下标记入种子节点列表
		}
		
		//清空除种子节点之外所有节点的标签列表
		for(int j=0;j<nodeSize;j++){
			if(!seed_node.contains(j)){//如果下标为j的点不是种子节点
				nodes_array[j].freeAllLabel();//清空标签
			}
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
