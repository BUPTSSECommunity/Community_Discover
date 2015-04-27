package data;

import java.util.HashMap;

public class Node {
  private static final String TAG = "Node";
  
  /**�ýڵ��ڹ��������еĶ�Ӧ�±�*/
  public int id;
  /**�ýڵ�����΢���û�����*/
  public String username;
  /**
   * ���ӽڵ����ǰ���ýڵ��ԭʼ��ǩ�б�
   * ���ӽڵ����󣺸ýڵ㱻���ϵı�ǩ�б�
   */
  public HashMap<String,Integer> label_list = new HashMap<String, Integer>();
  /**�ýڵ��ʼ��ǩ*/
  public String label;
  /**�ڵ����*/
  public int degree;
  
  /**
   * ���ǩ�б������һ����ǩ������ñ�ǩ���ڣ����ǩ������1
   * @param newLabel ��ǩ����
   * @return �ݶ�Ϊtrue
   */
  public boolean addLabel(String newLabel)
  {
	  Integer count = label_list.get(newLabel);
	  if(count == null){
		  label_list.put(newLabel, 1);
	  }else{
		  label_list.put(newLabel, count+1);
	  }
	  
	  return true;
  }
  
  /**
   * ��ձ�ǩ�б�����б�ǩ
   * @return �ݶ�Ϊtrue
   */
  public boolean freeAllLabel(){
	  label_list.clear();
	  return true;
  }
  
}
