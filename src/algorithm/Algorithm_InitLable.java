package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Node;

public class Algorithm_InitLable {
	/**
	 * 将数据集处理成节点列表和关联数组
	 * @param filename 数据集文件路径
	 * @param nodes_array 用于保存用户节点的列表
	 * @return 用于保存节点关联的矩阵
	 */
	public int[][] execute(String filename, Node[] nodes_array/*in/out*/){
		Map<String,Integer> map = new HashMap<String,Integer>();//用于保存微博id到数组下标的映射，便于对微博id查重
		int nodeSize = 0;//保存最终节点的数量
		int dataSize = 10;//表示文件的行数
		
		int[][]tempMat = new int[dataSize][dataSize];//明显大于实际关联矩阵的临时矩阵，最终根据nodeSize将有效部分赋予最终矩阵
		int[][]resultMat = null;//最终关联【矩阵】
		
		List<Node> nodeList = new ArrayList<Node>();//用于保存每个用户节点，用户id到list下标的映射存在map中;该list最终要转为【数组】
		
		try {
			InputStreamReader fr = new InputStreamReader(new FileInputStream(filename));
			
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			
			while(line != null){
				line = br.readLine();
				if(line.equals(null))
					break;
				
				String[] column = line.split(",");
				
				String userId_1 = column[1];
				String userId_2 = column[2];
				String user1Label = column[3];
				
				//判断两个user节点是否已经存在
				if(map.containsKey(userId_1) == false){//如果不存在，则创建新节点，添加Map映射
					Node node = new Node();
					node.id = nodeSize;
					node.username = userId_1;
					nodeList.add(node);
					//添加映射
					map.put(userId_1, nodeSize);
					nodeSize++;
				}
				if(map.containsKey(userId_2) == false){//如果不存在，则创建新节点，添加Map映射
					Node node = new Node();
					node.id = nodeSize;
					node.username = userId_2;
					nodeList.add(node);
					//添加映射
					map.put(userId_2, nodeSize);
					nodeSize++;
				}
				
				int index1 = map.get(userId_1);//获取user1节点下标
				nodeList.get(index1).addLabel(user1Label);//将该行标签打到user1上
				
				//关联该行的2个用户节点
				int index2 = map.get(userId_2);//获取user2节点下标
				tempMat[index1][index2] = 1;
				tempMat[index2][index1] = 1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将临时矩阵复制到最终矩阵
		resultMat = new int[nodeSize][nodeSize];
		for(int i=0;i<nodeSize;i++){
			for(int j=0;j<nodeSize;j++){
				resultMat[i][j] = tempMat[i][j];
			}
		}
		//将nodeList转为数组
		nodes_array = new Node[nodeSize];
		nodes_array = (Node[])nodeList.toArray();
		
		return resultMat;
	}
}
