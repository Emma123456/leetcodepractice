package homework2.heap;

public class HeapSort {
    /**
     * 实现堆排序,从小到大
     * a[i]的左子节点是2*i+1，右子节点是2*i+2,父节点是(i-1)/2
     * @param a
     */
    public void sort(int[] a){
        if(a == null || a.length ==0) return;
        buildHeap(a);
        int n = a.length;
        for(int i = n -1; i>0; i--){
            swap(a,i,0);
            heapify(a,0,i-1);
        }
    }

    /**
     * 从上到下堆化
     * @param a
     * @param s
     *          从下标=s的元素开始堆化
     * @param max
     *          下标最大值
     */
    private void heapify(int[] a, int s, int max) {
        int i = s;
        while(true){
            int maxPos = i;
            if(2*i+1<=max && a[i] < a[2*i+1]){
                maxPos = 2*i+1;
            }
            if(s*i+2<=max && a[maxPos] < a[2*i+2]){
                maxPos = 2*i  + 2;
            }
            if(maxPos == i) break;
            swap(a, maxPos, i);
        }
    }

    /**
     * 建一个大顶堆
     * @param a
     */
    private void buildHeap(int[] a) {
        //最大的非叶子节点是
        int n = a.length - 1;
        for(int i = (n -1)/2; i>=0; i--){
            heapify(a, i ,n);
        }
    }

    private void swap(int[] a ,int i, int  j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args){
        int[] nums = new int[]{10,4,1,5,8,6};
        new HeapSort().sort(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+",");
        }
    }
}
