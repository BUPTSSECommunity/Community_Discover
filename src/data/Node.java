package data;

import java.util.HashMap;

public class Node {
  private static final String TAG = "Node";
  
  /**该节点在关联矩阵中的对应下标*/
  public int id;
  /**该节点代表的微博用户名称*/
  public String username;
  /**
   * 种子节点求解前：该节点的原始标签列表；
   * 种子节点求解后：该节点被打上的标签列表
   */
  public HashMap<String,Integer> label_list = new HashMap<String, Integer>();
  /**该节点初始标签*/
  public String label;
  /**节点度数*/
  public int degree;
  
  /**
   * 向标签列表中添加一个标签，如果该标签存在，则标签计数加1
   * @param newLabel 标签名称
   * @return 暂定为true
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
   * 清空标签列表的所有标签
   * @return 暂定为true
   */
  public boolean freeAllLabel(){
	  label_list.clear();
	  return true;
  }
  
}
