package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Node;

public class Algorithm_LPA {
	/**
	 * 标签扩散的过程
	 * @param nodes_array 所有节点，其中只有种子节点含有标签，且为种子标签 
	 * @param links_matrix 邻接矩阵
	 * @param t 扩散阈值
	 * @param seedNode 种子节点在nodes_array中的下标
	 */
	public void execute(Node[] nodes_array,int[][] links_matrix, int t, List<Integer> seedNode){
		boolean is_stable = false;
		Map<Integer, Integer> M = new HashMap<Integer, Integer>();
		Map<Integer, List<Integer>> SN = new HashMap<Integer, List<Integer>>();
		int num = seedNode.size(); // 种子标签的个数 
		
		
		for (int i = 0; i < num; i++) {
			SN.put(Integer.valueOf(i), new ArrayList<Integer>());
			SN.get(i).add(seedNode.get(i));
		}
		
		while (!is_stable) {
			M.clear();
			is_stable = true;
			for (int i = 0; i < num; i++) {  // 某个标签
				String label = nodes_array[seedNode.get(i)].label;
				for (int j = 0; j < SN.get(i).size(); j++) {  // 该标签的版图
					for (int k = 0; k < nodes_array.length; k++) { // 版图中某节点的相邻节点
						if (links_matrix[SN.get(i).get(j)][k] == 1){
							if (nodes_array[k].label_list.size() == 0){ // 没有标签
								nodes_array[k].addLabel(label);
								if (!M.containsValue(k))
									M.put(Integer.valueOf(k), Integer.valueOf(k));
								is_stable = false;
							} else if (nodes_array[k].label_list.containsKey(label)){ // 拥有Si标签
								continue;
							} else { // 该节点拥有非Si标签
								int count = 0; // 该节点周围的Si标签数
								for (int n = 0; n < nodes_array.length; n++) {
									if (links_matrix[k][n] == 1) {
										if (nodes_array[n].label_list.containsKey(label)) {
											++count;
										}
									}
								}
								if (count >= t) { // 可以给该节点添加标签
									nodes_array[k].addLabel(label);
									if (!M.containsValue(k))
										M.put(Integer.valueOf(k), Integer.valueOf(k));
									is_stable = false;
								}
							}
						}
						
					}
				}
				
				// M加入SNi
				SN.get(i).addAll(M.values());
				//清空M
				M.clear();
			}
		}
		
	}
}
