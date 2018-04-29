package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        LinkedList<Integer> valueList = new LinkedList<Integer>();
        dfs(root,valueList,result);
        return result;
    }
    private void dfs(TreeNode node ,LinkedList<Integer> valueList, List<String> result){
        if(node==null) {  
            return;
        }
        if(node.left==null && node.right==null){
        	valueList.add(node.val);
            String str = valueList.get(0)+"";
            for(int i=1;i<valueList.size();i++){
                str +="->"+valueList.get(i);
            }
            result.add(str);
        }else{
        	valueList.add(node.val);
        	if(node.left!=null){
        		dfs(node.left,valueList,result);
        		valueList.removeLast();
        	}
            if(node.right!=null){
            	dfs(node.right,valueList,result);
            	valueList.removeLast();
            }
        }
    }
	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		node.left = node1;
		TreeNode node2 = new TreeNode(3);
		node.right = node2;
		
		TreeNode node3 = new TreeNode(5);
		node1.right =node3;
		List<String> result = new BinaryTreePaths().binaryTreePaths(node);
		System.out.println(result);
	}

}
