package algorithm;

import java.util.ArrayList;
import java.util.List;

import data.Node;

public class Algorithm_SeedNode {
	/**
	 * ������ӽڵ㣬�������ӽڵ��м����Ӧ�����ӱ�ǩ
	 * @param nodes_array ִ��ǰ�����нڵ��б�ִ�к��б��е����ӽڵ��Ѱ������ӱ�ǩ
	 * @param link_matrix �ڵ��������
	 * @param seed_label ���ӱ�ǩ�����б�
	 * @param x ƽ�����ӣ���ʱ�������0.5��ѡȡ���ӽڵ�ʱ��xԽ����ڵ����Խ��Ҫ��xԽС�����ӱ�ǩ��ԭʼ��ǩ�г��ֵĴ���Խ��Ҫ
	 * @return ���ӽڵ��� nodes_array �еĶ�Ӧ�±��б�
	 */
	public int[] execute(Node[] nodes_array/*in/out*/, int[][] link_matrix/*in*/,String[] seed_label/*in*/,double x){
		int K = seed_label.length;//���ӱ�ǩ����
		int[] seed_node = new int[K];//������Ҫ���ص����ӽڵ��б�
		int nodeSize = link_matrix.length;//�ڵ������
		
		for(int i=0;i<K;i++){
			double[] suit = new double[nodeSize];//Ϊ���нڵ㽨����Ե�i�����ӱ�ǩ��suitֵ�б�
			int maxSuitIndex = 0;
			//�������нڵ㣬���ÿ���ڵ�j����suitֵ
			for(int j=0;j<nodeSize;j++){
				double degree = (double)countDegree(j,link_matrix);//�õ��ڵ�j�Ķ�
				double appear = (double)nodes_array[j].label_list.get(seed_label[i]);//��ǩi�ڽڵ�j��ԭʼ��ǩ�г��ֵĴ���
				
				suit[j] = degree*x + appear*(1-x);//����ڵ�j��suit
				//��¼���suitֵ���±�
				if(suit[j] >= suit[maxSuitIndex]){
					maxSuitIndex = j;
				}
			}
			
			//�����±�Ϊ maxSuitIndex �Ľڵ㣬��ձ�ǩ�б��������ӱ�ǩi
		}
		
		return seed_node;
	}
	
	/**
	 * ����ָ���ڵ�Ķ�
	 * @param nodeIndex ָ���ڵ��ھ�����±�
	 * @param link_matrix ��������
	 * @return �ýڵ�Ķ�
	 */
	private int countDegree(int nodeIndex, int[][] link_matrix){
		int result = 0;//����ڵ�Ķ�
		int nodeSize = link_matrix.length;
		//��������ĵ�nodeIndex�У�����1������
		for(int i=0;i<nodeSize;i++){
			if(link_matrix[nodeIndex][i] == 1){
				result++;
			}
		}
		return result;
	}
}
