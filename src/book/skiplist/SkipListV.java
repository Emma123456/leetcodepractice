package book.skiplist;

import java.util.Random;

public class SkipListV<T> {
    private SkipListNode<T> head,tail;
    //节点数量
    private int nodeCount;
    //索引层数
    private int listLevel;
    private Random random;
    private static final double PROBABILITY=0.5;//向上提升一个的概率
    public SkipListV(){
        random = new Random();
        clear();
    }

    /**
     * 清空列表
     */
    public void clear(){
        head = new SkipListNode<T>(SkipListNode.HEAD_KEY,null);
        tail = new SkipListNode<T>(SkipListNode.TAIL_KEY,null);
        horizontalLink(head,tail);
        nodeCount = 0;
        listLevel = 0;
    }

    public boolean isEmpty(){
        return nodeCount==0;
    }

    public int size() {
        return nodeCount;
    }


    /**
     * 在最下面一层找到key所在的节点位置
     * @param key
     * @return
     */
    private SkipListNode findNode(int key){
        SkipListNode<T> p = head;
        while(true){
            while(p.right.key!=SkipListNode.TAIL_KEY && p.right.key<=key){
                p = p.right;
            }
            if(p.down!=null){
                p = p.down;
            }else{
                break;
            }
        }
        return p;
    }

    /**
     * 查找关键词等于key的节点
     * @param key
     * @return
     */
    public SkipListNode<T> search(int key){
        SkipListNode<T> node = findNode(key);
        if(node!=null && node.key == key){
            return node;
        }else{
            return  null;
        }
    }

    /**
     * 插入数据
     * @param key
     * @param value
     */
    public void put(int key,T value){
        SkipListNode<T> pre = findNode(key);
        if(pre.key == key){
            pre.value = value;
            return;
        }
        SkipListNode<T> next = pre.right;
        SkipListNode<T> newNode = new SkipListNode<T>(key,value);
        horizontalLink(pre,newNode);
        horizontalLink(newNode,next);


        int currentLevel = 0;
        SkipListNode<T> p = pre;
        while (random.nextDouble()<PROBABILITY) {
            if(currentLevel>=listLevel){
                listLevel++;

                SkipListNode<T> newHead = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
                SkipListNode<T> newTail = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
                horizontalLink(newHead,newTail);
                vertiacallLink(newHead,head);
                vertiacallLink(newTail,tail);

                head = newHead;
                tail = newTail;
            }

            while(p.up==null){
                p = p.left;
            }
            p =p.up;
            SkipListNode<T> n = new SkipListNode<T>(key,null);
            SkipListNode<T> nextTmp = p.right;
            horizontalLink(p,n);
            horizontalLink(n,nextTmp);
            vertiacallLink(n,newNode);

            newNode = n;
            currentLevel++;
        }
        nodeCount++;
    }

    /**
     * 两个节点水平双向链接
     * @param leftNode
     * @param rightNode
     */
    private void horizontalLink(SkipListNode<T> leftNode, SkipListNode<T> rightNode) {
        leftNode.right = rightNode;
        rightNode.left = leftNode;
    }

    /**
     * 垂直链接
     * @param topNode
     * @param bottomNode
     */
    private void vertiacallLink(SkipListNode<T> topNode,SkipListNode<T> bottomNode){
        topNode.down=bottomNode;
        bottomNode.up=topNode;
    }

    /**
     * 打印出原始数据
     * */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            return "跳跃表为空！";
        }
        StringBuilder builder=new StringBuilder();
        SkipListNode<T> p=head;
        while (p.down!=null) {
            p=p.down;
        }

        while (p.left!=null) {
            p=p.left;
        }
        if (p.right!=null) {
            p=p.right;
        }
        while (p.right!=null) {
            builder.append(p);
            builder.append("\n");
            p=p.right;
        }

        return builder.toString();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SkipListV<String> list=new SkipListV<String>();
        System.out.println(list);
        list.put(2, "yan");
        list.put(1, "co");
        list.put(3, "feng");
        list.put(1, "cao");//测试同一个key值
        list.put(4, "曹");
        list.put(6, "丰");
        list.put(5, "艳");
        System.out.println(list);
        System.out.println(list.size());
    }
}
