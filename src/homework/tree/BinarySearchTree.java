package homework.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉查找树
 * 每个节点的值大于左子树所有节点的值，小于右子树所有的值
 */
public class BinarySearchTree<T extends Comparable> {
    private TreeNode root;
    /**
     * 插入数据
     * @param data
     */
    public void insert(T data){
        if(root == null){
            root = new TreeNode(data,null,null);
        }else{
            //比较data与currentNode.data大小。如果data<currentNode.data,则继续比较data与currentNode.left上节点大小。
            TreeNode currentNode = root;
            while(currentNode!=null){
                if(currentNode.data.compareTo(data)>0){

                    if(currentNode.left==null){
                        currentNode.left = new TreeNode(data,null,null);
                        break;
                    }else{
                        currentNode = currentNode.left;
                    }
                }else if(currentNode.data.compareTo(data)<0){
                    if(currentNode.right==null){
                        currentNode.right = new TreeNode(data,null,null);
                        break;
                    }else{
                        currentNode = currentNode.right;
                    }
                }else{
                    break;
                }
            }

        }
    }

    /**
     * 查找data是否存在
     * @param data
     * @return
     */
    public boolean search(T data){
        TreeNode p = root;
        while(p!=null && !p.data.equals(data)){
            if(p.data.compareTo(data)>0){
                p = p.left;
            }else{
                p = p.right;
            }
        }
        return p==null?false:true;
    }

    public void delete(T data){
        TreeNode<T> tmp = root;
		TreeNode<T> parent = null;
		while (tmp != null) {
			int r = tmp.data.compareTo(data);
			if (r > 0) {
				parent = tmp;
				tmp = tmp.leftChild;
			} else if (r < 0) {
				parent = tmp;
				tmp = tmp.rightChild;
			} else {
				break;
			}
		}

		if (tmp != null) {
			// 删除叶子节点
			if (tmp.leftChild == null && tmp.rightChild == null) {
				if (parent.leftChild == tmp) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
			} else if (tmp.leftChild != null && tmp.rightChild != null) {
				// 找到右子树上的最小值，替换
				TreeNode<T> minNode = tmp.rightChild;
				TreeNode<T> parentOfMin = tmp;
				while (minNode.leftChild != null) {
					parentOfMin = minNode;
					minNode = minNode.leftChild;
				}
				tmp.data = minNode.data;
				// 如何删除maxNode
				parentOfMin.leftChild = null;
			} else if (tmp.leftChild != null) {
				if (parent.leftChild == tmp) {
					parent.leftChild = tmp.leftChild;
				} else {
					parent.rightChild = tmp.leftChild;
				}
			} else if (tmp.rightChild != null) {
				if (parent.leftChild == tmp) {
					parent.leftChild = tmp.rightChild;
				} else {
					parent.rightChild = tmp.rightChild;
				}
			}

		}
    }

    /**
	 * 二叉树的层次遍历
	 */
	public void levelPrint() {
		int depth = this.getDepth();
		if (depth == 0)
			return;
		int count = (int) Math.pow(2, depth-1);
		// 结果集
		List<List<TreeNode<T>>> tree = new ArrayList<List<TreeNode<T>>>();
		for (int i = 0; i <= depth; i++) {
			ArrayList<TreeNode<T>> list = new ArrayList<TreeNode<T>>();
			for (int j = 0; j < count; j++) {
				list.add(null);
			}
			tree.add(list);
		}
		// 设置index
		int index = (count - 1) / 2;
		root.index = index;
		tree.get(0).set(index, root);
		Queue<TreeNode<T>> queue = new LinkedList<>();
		queue.offer(root);
		int level = 1;
		while (!queue.isEmpty()) {
			level++;
			int dis = (int) (count / Math.pow(2, level));
			dis = (dis <= 0 ? 1 : dis);
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				TreeNode<T> node = queue.poll();
				if (node.leftChild != null) {
					int leftIndex = node.index - dis;
					node.leftChild.index = leftIndex;
					tree.get(level - 1).set(leftIndex, node.leftChild);
					if(node.leftChild.leftChild != null || node.leftChild.rightChild!=null) {
						queue.offer(node.leftChild);
					}
					
				}
				if (node.rightChild != null) {
					int rightIndex = node.index + dis;
					node.rightChild.index = rightIndex;
					tree.get(level - 1).set(rightIndex, node.rightChild);
					if(node.rightChild.leftChild!=null || node.rightChild.rightChild!=null) {
						queue.offer(node.rightChild);
					}
					
				}
			}
		}
		// 打印
		printNode(tree);

	}

	private void printNode(List<List<TreeNode<T>>> tree) {
		
		for (int i = 0; i < tree.size(); i++) {
			List<TreeNode<T>> list = tree.get(i);
			System.out.println();
			for(int j=0;j<list.size();j++) {
				System.out.print(list.get(j)==null?"\t":list.get(j).data+"\t");
			}
		}
		System.out.println();
	}
    
    /**
	 * 获取树的深度
	 * 
	 * @return
	 */
	public int getDepth() {
		return getDepth(root);
	}

	private int getDepth(TreeNode<T> node) {
		if (node == null)
			return 0;
		return Math.max(getDepth(node.leftChild), getDepth(node.rightChild)) + 1;
	}

    class TreeNode{
        T data;
        TreeNode left;
        TreeNode right;
        TreeNode(T data,TreeNode left,TreeNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
