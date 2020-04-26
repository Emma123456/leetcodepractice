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

    }

    /**
     * 二叉树的层次遍历
     */
    public void levelPrint(){
        Queue<TreeNode> queue = new LinkedList<>();
        if(root!=null){
            queue.offer(root);
        }

        List<List<T>> list = new ArrayList<List<T>>();
        int level = 0;
        while(!queue.isEmpty()){
            level++;
            int size = queue.size();
            List<T> values = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if(node==null){
                    values.add(null);
                }else{
                    values.add(node.data);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }


            }
            list.add(values);
        }
        for(int i=0;i<list.size();i++){
            System.out.println();

            for(int j =0;j<list.get(i).size();j++){
                for(int k = 0;k<level - i+1;k++){
                    System.out.print(" ");
                }
                System.out.print(list.get(i).get(j)==null?" ":list.get(i).get(j)+" ");
            }
        }
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
