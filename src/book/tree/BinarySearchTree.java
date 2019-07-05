package book.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import  java.util.List;
/***
 * 二叉查找树
 * 节点的值大于左子树所有的值；节点的值小于右子树所有的值
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable> {

    private class Node{
        private T data;
        private Node left;
        private Node right;

        public Node(T data){
            this.data = data;

        }
    }

    private Node root;


    /**
     * 插入
     * @param data
     */
    public void insert(T data){
        if(root==null){
            root = new Node(data);
        }else{
            Node node = root;
            while(node!=null){
                if(node.data.compareTo(data)>0){
                    if(node.left==null){
                        node.left = new Node(data);
                        break;
                    }else{
                        node = node.left;
                    }
                }
                if(node.data.compareTo(data)<0){
                    if(node.right==null){
                        node.right = new Node(data);
                        break;
                    }else{
                        node = node.right;
                    }
                }
            }

        }
    }

    /**
     * 删除一个元素。
     * 如果这个元素没有左右子节点，直接删除，node.parent.child = null
     * 如果这个元素有一个左子节点，
     * @param data
     */
    public void delete(T data){
        Node node = root;
        Node parent = null;
        while(node!=null && node.data!=data){
            parent = node;
            if(node.data.compareTo(data)>0){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        //没有找到data
        if(node==null) return;

        if(node.left!=null && node.right!=null){
            //左右子节点不为空，那就在右子树查找最小值，将node的值设置为最小值，同时删除最小元素
            Node minNode = node.right;
            Node parentOfMinNode = node;
            while( minNode.left!=null){
                parentOfMinNode = minNode;
                minNode = minNode.left;
            }
            node.data = minNode.data;
            node = minNode;
            parent = parentOfMinNode;

        }
        Node child = null;
        if (node.left!=null){
            child = node.left;
        }else if(node.right!=null){
            child = node.right;
        }
        //删除的树根节点
        if(parent == null){
            root = child;
        }else if(parent.left==node){
            parent.left = child;
        }else{
            parent.right = child;
        }
    }

    /**
     * 查找data在树中是否存在
     * @param data
     * @return
     */
    public boolean find (T data){
        Node node = root;
        while(node!=null){
            if(node.data.compareTo(data)==0){
                return true;
            }
            if(node.data.compareTo(data)>0){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return false;
    }

    /**
     * 查找最小值
     * @return
     */
    public T findMin(){
        Node node = root;
        while(node!=null && node.left!=null){
            node = node.left;
        }

        return  node!=null?node.data:null;
    }

    /**
     * 查找最大值
     * @return
     */
    public T findMax(){
        Node node = root;
        while(node!=null && node.right!=null){
            node = node.right;
        }

        return  node!=null?node.data:null;

    }

    /**
     * 层次遍历
     */
    public void levelPrint(){
        Queue<Node> queue = new LinkedList<>();
        if(root!=null){
            queue.offer(root);
        }
        List<List<T>> valueList = new ArrayList<List<T>>();
        while (!queue.isEmpty()){
            int size = queue.size();
            List<T> list = new ArrayList<T>();
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                if(node!=null){
                    list.add(node.data);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }

            }
            valueList.add(list);
        }
        for(List<T> list : valueList){
            System.out.println(list);
        }
    }
}
