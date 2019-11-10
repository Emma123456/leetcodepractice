package homework.tree;

/**
 * 堆：一棵完全二叉树
 * 最大堆：堆顶元素大于左右子树中的元素
 * 存储堆：用数组 父节点下标i，左子节点：2i+1，右子节点：2i+2
 */
public class Heap<T extends Comparable> {
    private int len = 11;
    private int size;
    private Object[] queue ;

    public Heap(){
        queue = new Object[len];
    }

    /**
     * 添加一个元素
     * @param data
     */
    public void offer(T data){
        if(data==null){
            throw new NullPointerException();
        }
        if(size==len){
            throw new RuntimeException("空间已满");
        }
        size++;
        queue[size-1] = data;
        siftUp(data,size-1);
    }


    /**
     * 删除堆顶元素
     * @return
     */
    public Object poll(){
        if(size==0) return null;
        Object obj = queue[0];
        queue[0] = queue[size-1];
        queue[size-1] = null;
        size--;
        siftDown((T)queue[0],0);
        return obj;
    }

    private void siftDown(T data, int k) {
        int half = size>>>1;
        while(k<half){
            int leftIdx = (k<<1)+1;
            int rightIdx = leftIdx+1;
            int maxPos = k;
            if(data.compareTo(queue[leftIdx])<0){
                maxPos = leftIdx;
            }
            if(rightIdx< size  && ((Comparable)queue[rightIdx]).compareTo(queue[maxPos])>0){
                maxPos = rightIdx;
            }
            if(maxPos==k){
                break;
            }
            queue[k] = queue[maxPos];
            queue[maxPos] = data;
            k=maxPos;
        }
        queue[k] = data;
    }

    /**
     * 沿着路径向上堆化
     * @param data
     * @param k
     */
    private void siftUp(T data, int k) {
        while(k>0){
            int parent = (k-1)>>>1;
            if(data.compareTo(queue[parent])>0){
                queue[k] = queue[parent];

            }else{
                break;
            }
            k = parent;
        }
        queue[k] = data;
    }
}
