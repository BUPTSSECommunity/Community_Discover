package algorithm;

import java.util.HashMap;
import java.util.Map;

import data.Node;

public class Algorithm_SeedLabel {
  /**
   * @param nodes_array 节点数组
   * @param links_matrix 邻接矩阵
   * @param k 阈值
   * @return seed_node_ref 种子标签列表
   */
  public String[] execute(Node[] nodes_array, int[] links_matrix, int k){
    String[] seed_node_ref = new String[k];
    Map<String, Label> map = new HashMap<String, Label>();
		
    for (int i = 0; i < nodes_array.length; i++) {
      //新加入标签
      if (!map.containsKey(nodes_array[i].label)) {
        Label label = new Label();
        label.degree = nodes_array[i].degree;
        label.total++;
        label.e = Math.pow(Math.E, -label.degree);
        label.name = nodes_array[i].label;
        map.put(nodes_array[i].label, label);
      } else { //已有的标签
        Label l = map.get(nodes_array[i].label);
        l.degree += nodes_array[i].degree;
        l.total++;
        l.e = l.e * Math.pow(Math.E, -nodes_array[i].degree);
      }
    }
    
    Label[] labels = map.values().toArray(new Label[map.size()]);
    
    //计算每个标签的影响力
    for (int i = 0; i < labels.length; i++) {
      labels[i].influence += labels[i].degree / labels[i].total + labels[i].e;
    }
    
    quick_sort(labels, 0, labels.length);
    
    for (int i = 0; i < seed_node_ref.length; i++) {
      seed_node_ref[i] = labels[i].name;
    }
    
		return seed_node_ref;
	}
  
  private class Label{
    /** 度数 */
    public int degree;
    /** 出现总次数 */
    public int total;
    /** e^(-d(Li)) */
    public double e;
    /** 影响力 */
    public double influence;
    /** 标签名 */
    public String name;
    
    public Label() {
      this.degree = 0;
      this.total = 0;
      this.e = 0.0;
      this.influence = 1.0;
    }
  }
    
  //快速排序
  private void quick_sort(Label s[], int l, int r)
  {
      if (l < r)
      {
          swap(s[l], s[(l + r) / 2]);
          int i = l, j = r;
          Label x = s[l];
          while (i < j)
          {
              while(i < j && s[j].influence >= x.influence) // 从右向左找第一个小于x的数
                 j--;  
              if(i < j) 
                s[i++] = s[j];
        
              while(i < j && s[i].influence < x.influence) // 从左向右找第一个大于等于x的数
                i++;  
              if(i < j) 
                s[j--] = s[i];
          }
          s[i] = x;
          quick_sort(s, l, i - 1); // 递归调用 
          quick_sort(s, i + 1, r);
      }
  }
  
  //交换标签，只需要交换名称和影响力即可
  private void swap(Label a, Label b) {
    String tmp_s = a.name;
    a.name = b.name;
    b.name = tmp_s;
    
    double d = a.influence;
    a.influence = b.influence;
    b.influence = d;
  }
}
