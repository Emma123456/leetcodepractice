package homework2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉查找树，根节点的值大于左子树中所有节点的值，并且小于等于右子树中所有节点的值
 */
public class BinarySearchTree<T extends Comparable> {
    private Node root;
    /**
     * 插入一个元素
     *
     * @param data
     */
    public void insert(T data) {
        if(root == null){
            root = new Node(data);
        }else{
            Node node = root;
            while(true){
                if(data.compareTo(node.data) >= 0){
                    if(node.right != null){
                        node = node.right;
                    }else{
                        node.right = new Node(data);
                        break;
                    }
                }else{
                    if(node.left != null){
                        node = node.left;
                    }else{
                        node.left = new Node(data);
                        break;
                    }
                }
            }
        }
    }


    /**
     * 查找一个元素。如果存在返回true，否则返回false
     * @param data
     * @return
     */
    public boolean find(T data){
        Node node = root;
        while(node != null){
            if(data.compareTo(node.data) == 0){
                return true;
            }
            if(data.compareTo(node.data) >0){
                node = node.right;
            }else{
                node = node.left;
            }
        }
        return false;
    }

    /**
     * 删除一个元素
     * @param data
     */
    public void delete(T data){
        Node node = root;
        Node pre = null;
        while(node != null){
            if(data.compareTo(node.data) == 0){
                if(node.left ==null && node.right ==null){
                    node.data = null;
                }else if(node.right == null){
                    //左子树不为空
                    if(pre != null){
                        pre.left = node.left;
                    }else{
                        root = node.left;
                    }

                }else{
                    //左右子树不为空
                    Node minNode = node.right;//要删除的节点
                    Node minNodeOfp = null;
                    while(minNode != null && minNode.left != null){
                        minNodeOfp = minNode;
                        minNode  = minNode.left;
                    }
                    node.data = minNode.data;
                    if(minNodeOfp == null){
                        minNode.data = null;
                    }else{
                        minNodeOfp.left = minNode.left;
                    }
                }
            }
            pre = node;
            if(data.compareTo(node.data) >0){
                node = node.right;
            }else{
                node = node.left;
            }
        }

    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        StringBuilder result = new StringBuilder();
        preOrder(root,result);
        System.out.println(result);
    }

    private void preOrder(Node node, StringBuilder result) {
        if(node != null){
            result.append(node.data +" ");
            preOrder(node.left,result);
            preOrder(node.right,result);
        }
    }

    /**
     * 中序遍历
     */
    public void middleOrder(){
        StringBuilder result = new StringBuilder();
        middleOrder(root,result);
        System.out.println(result);
    }

    private void middleOrder(Node node, StringBuilder result) {
        if(node != null){
            middleOrder(node.left,result);
            result.append(node.data +" ");
            middleOrder(node.right,result);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        StringBuilder result = new StringBuilder();
        postOrder(root,result);
        System.out.println(result);
    }

    private void postOrder(Node node, StringBuilder result) {
        if(node != null){
            postOrder(node.left,result);
            postOrder(node.right,result);
            result.append(node.data +" ");
        }
    }

    /**
     * 查找最小值的元素
     * @return
     */
    public T findMin(){
        Node node = root;

        while(node != null && node.left !=null){
            node = node.left;
        }
        return node != null? node.data : null;
    }

    /**
     * 查找最大值的元素
     * @return
     */
    public T findMax(){
        Node node = root;

        while(node != null && node.right !=null){
            node = node.right;
        }
        return node != null? node.data : null;
    }

    /**
     * 二叉树的层次遍历
     */
    public void levelPrint(){
        Queue<Node> queue = new LinkedList<>();
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
                Node node = queue.poll();
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

    class Node{
        T data;
        Node left;
        Node right;

        public Node(T data) {
            this.data = data;
        }
    }
}
