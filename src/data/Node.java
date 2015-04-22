package data;

import java.util.List;

public class Node {
  private static final String TAG = "Node";
  
  public int id;
  public String username;
  public List<String> label_list;
  public String label;
  
  //add a label to this node , stored in label_list
  public boolean addLabel(String newLabel)
  {
	  label_list.add(newLabel);
	  return true;
  }
  
  //free all the labels in label_list
  public boolean freeAllLabel(){
	  label_list.clear();
	  return true;
  }
  
}
