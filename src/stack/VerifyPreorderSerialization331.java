package stack;

import java.util.ArrayList;
import java.util.List;

public class VerifyPreorderSerialization331 {
	private int index = 0;
	/**
	 * 注意：1 非空节点一定有两个空节点
	 * 按着操作顺序走一遍，觉得自己应该能写出代码；发现写不出来
	 * 分开访问节点与判断节点是否有效，其实他们应该是等价的
	 * 因为是一颗二叉树，所以最终就是判断preorder[0]是不是一个有效节点(注意2：可能有多余的节点)
	 * preorder[0]是否有效就要看preorder[0]的左右节点是否有效。
	 * 	preorder[0]的左节点preorder[0+1]
	 * 	preorder[0]的右节点不知道，只有等遍历完了左节点才知道
	 * 
	 * 一个节点是否有效：如果是#，则空节点，是有效节点。如果非#，则要判断左右节点是否有效
	 * @param preorder
	 * @return
	 */
	public boolean isValidSerialization(String preorder) {
		String[] strs = preorder.split(",");
		index = 0;
		return checkNode(strs) && index == strs.length-1;
    }
	
	private boolean checkNode(String[] preorder){
		if(index >= preorder.length) return false;
		String node = preorder[index];
		if(!"#".equals(node)){
			++index;
			boolean left = checkNode(preorder);
			++index;
			boolean right =  checkNode(preorder);
			return left && right;
		}
		return true;
	}
	
	/**
	 * 所有的叶子节点是空节点，说明这颗二叉树是一颗满树。二叉树满树有一个特性：叶子节点数量=非叶子节点数量+1
	 * 注意：有多余的节点
	 * @param preorder
	 * @return
	 */
	public boolean isValidSerializationV2(String preorder) {
		String[] nodes = preorder.split(",");
		int nonLeaves = 0,leaves = 0,i =0 ;
		for(;i<nodes.length && nonLeaves+1 != leaves;i++){
			if(nodes[i].equals("#")) leaves++;
			else nonLeaves++;
		}
		return nonLeaves+1 == leaves  && i==nodes.length;
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("9,3,4,#,#,1,#,#,2,#,6,#,#");
		list.add("1,#");
		list.add("9,#,#,1");

		for(String preorder : list){
			boolean r = new VerifyPreorderSerialization331().isValidSerializationV2(preorder);
			System.out.println(r);
		}
		
	}

}
