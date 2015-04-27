package algorithm;

import java.util.HashMap;
import java.util.Map;

import data.Node;

public class Algorithm_SeedLabel {
  /**
   * @param nodes_array �ڵ�����
   * @param links_matrix �ڽӾ���
   * @param k ��ֵ
   * @return seed_node_ref ���ӱ�ǩ�б�
   */
  public String[] execute(Node[] nodes_array, int[] links_matrix, int k){
    String[] seed_node_ref = new String[k];
    Map<String, Label> map = new HashMap<String, Label>();
		
    for (int i = 0; i < nodes_array.length; i++) {
      //�¼����ǩ
      if (!map.containsKey(nodes_array[i].label)) {
        Label label = new Label();
        label.degree = nodes_array[i].degree;
        label.total++;
        label.e = Math.pow(Math.E, -label.degree);
        label.name = nodes_array[i].label;
        map.put(nodes_array[i].label, label);
      } else { //���еı�ǩ
        Label l = map.get(nodes_array[i].label);
        l.degree += nodes_array[i].degree;
        l.total++;
        l.e = l.e * Math.pow(Math.E, -nodes_array[i].degree);
      }
    }
    
    Label[] labels = map.values().toArray(new Label[map.size()]);
    
    //����ÿ����ǩ��Ӱ����
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
    /** ���� */
    public int degree;
    /** �����ܴ��� */
    public int total;
    /** e^(-d(Li)) */
    public double e;
    /** Ӱ���� */
    public double influence;
    /** ��ǩ�� */
    public String name;
    
    public Label() {
      this.degree = 0;
      this.total = 0;
      this.e = 0.0;
      this.influence = 1.0;
    }
  }
    
  //��������
  private void quick_sort(Label s[], int l, int r)
  {
      if (l < r)
      {
          swap(s[l], s[(l + r) / 2]);
          int i = l, j = r;
          Label x = s[l];
          while (i < j)
          {
              while(i < j && s[j].influence >= x.influence) // ���������ҵ�һ��С��x����
                 j--;  
              if(i < j) 
                s[i++] = s[j];
        
              while(i < j && s[i].influence < x.influence) // ���������ҵ�һ�����ڵ���x����
                i++;  
              if(i < j) 
                s[j--] = s[i];
          }
          s[i] = x;
          quick_sort(s, l, i - 1); // �ݹ���� 
          quick_sort(s, i + 1, r);
      }
  }
  
  //������ǩ��ֻ��Ҫ�������ƺ�Ӱ��������
  private void swap(Label a, Label b) {
    String tmp_s = a.name;
    a.name = b.name;
    b.name = tmp_s;
    
    double d = a.influence;
    a.influence = b.influence;
    b.influence = d;
  }
}
