package homework;

import java.util.Random;

/**
 * 代码来源于小灰 http://i.csdn.net
 */
public class SkipList {
    private static final double PROMOTE_RATE = 0.5;
    private Node head;
    private Node tail;
    private int maxLevel = 0;
    public SkipList(){
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }


    /**
     * 查找节点
     * @param data
     * @return
     */
    public boolean search(int data){
        Node p = searchNode(data);
        return p.data == data;
    }

    /**
     * 插入节点
     * @param data
     */
    public void insert(int data){
        Node preNode = searchNode(data);
        if(preNode.data == data) return;
        Node newNode = new Node(data);
        appendNode(preNode,newNode);
        int currentLevel = 0;
        Random random = new Random();
        boolean addAble = true;
        while(addAble && random.nextDouble() < PROMOTE_RATE){
            if(currentLevel == maxLevel){
                addLevel();
                addAble = false;
            }
            while(preNode.up == null){
                preNode = preNode.left;
            }
            preNode = preNode.up;
            Node newUpperNode = new Node(data);
            appendNode(preNode,newUpperNode);
            newNode.up = newUpperNode;
            newUpperNode.down = newNode;
            newNode = newUpperNode;
            currentLevel++;
        }
    }

    /**
     * 删除节点data。如果节点不存在返回false，否则返回true.
     * @param data
     * @return
     */
    public boolean remove(int data){
        Node node = searchNode(data);
        if(node.data!=data) return false;
        int currentLevel = 0;
        while(node!=null){
            node.left.right = node.right;
            node.right.left = node.left;
            if(currentLevel>0 && node.left.data == Integer.MIN_VALUE && node.right.data==Integer.MAX_VALUE){
                removeLevel(node.left);
            }else{
                currentLevel++;
            }
            node = node.up;
        }
        return true;
    }

    public void printList(){
        Node node = head;
        while(node.down!=null){
            node = node.down;
        }
        while(node.right.data!=Integer.MAX_VALUE){
            System.out.print(node.right.data+"->");
            node = node.right;
        }
        System.out.println();
    }
    private void  removeLevel(Node levelHead){
        Node levelTail = levelHead.right;
        //删除最高层
        if(levelHead.up == null){
            head = levelHead.down;
            tail = levelTail.down;
            levelHead.down.up = null;
            levelTail.down.up = null;
        }else{
            levelHead.up.down =  levelHead.down;
            levelHead.down.up =  levelHead.up;
            levelTail.up.down = levelTail.down;
            levelTail.down.up = levelTail.up;
        }
        levelHead.right = null;
        levelTail.left = null;
        maxLevel--;
    }

    /**
     * 添加一层
     */
    private void addLevel() {
        maxLevel++;
        Node levelHead = new Node(Integer.MIN_VALUE);
        Node levelTail = new Node(Integer.MAX_VALUE);
        levelHead.right = levelTail;
        levelTail.left = levelHead;

        head.up = levelHead;
        levelHead.down = head;
        head = levelHead;

        levelTail.down = tail;
        tail.up = levelTail;
        tail = levelTail;
    }

    private void appendNode(Node preNode,Node newNode){
        newNode.left = preNode;
        newNode.right = preNode.right;
        preNode.right.left = newNode;
        preNode.right = newNode;
    }

    /**
     * 返回的节点值可能等于data，也可能是data需要插入的位置，也就是datade前驱节点
     * @param data
     * @return
     */
    private Node searchNode(int data){
        Node node = head;
        while(true){
            while(node.right.data!=Integer.MAX_VALUE  && node.right.data<=data ){
                node = node.right;
            }
            if(node.down == null) break;
            node = node.down;
        }
        return node;
    }
    private class Node{
        public int data;
        private Node right,left,up,down;

        public Node(int data){
            this.data = data;
        }
    }
}
