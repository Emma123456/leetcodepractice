package stack;

import java.util.ArrayList;
import java.util.List;
/**
 * 这种做法空间不符合要求，空间大小超过了树的高度
 * O(h) memory, where h is the height of the tree
 * @author Administrator
 *
 */
public class BSTIterator {
	private List<Integer> valueList = new ArrayList<Integer>();
	private int index = 0;
	public BSTIterator(TreeNode root) {
		if(root!=null){
			traversal(root);
		}
        
    }

    private void traversal(TreeNode node) {
		if(node.left!=null){
			traversal(node.left);
		}
		valueList.add(node.val);
		if(node.right!=null){
			traversal(node.right);
		}
	}

	/** @return whether we have a next smallest number */
    public boolean hasNext() {
        return index < valueList.size();
    }

    /** @return the next smallest number */
    public int next() {
        return valueList.get(index++);
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		BSTIterator i = new BSTIterator(root);
		while (i.hasNext()) System.out.println(i.next());
	}

}
