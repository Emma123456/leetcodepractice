package book.tree;

/**
 * 堆排序
 */
public class HeapSort<T extends Comparable> {
    private T[] nums;
    private int size;

    /**
     * 从小到大排序数组nums，下标从0开始存储数据
     * @param nums
     */
    public T[] sort(T[] nums){
        this.nums = nums;
        this.size = nums.length;
        buildHeap();
        sort();
        return nums;
    }

    private void buildHeap() {
        for(int i=size/2;i>=0;i--){
            heapify(i,size);
        }
    }

    /**
     * 从上到下堆化
     * @param idx
     */
    private void heapify(int idx,int n) {
        while(true){
            int maxPos = idx;
            if(2*idx+1<n && nums[idx].compareTo(nums[2*idx+1])<0){
                maxPos = 2*idx+1;
            }
            if(2*idx+2<n && nums[maxPos].compareTo(nums[2*idx+2])<0){
                maxPos = 2*idx+2;
            }
            if(maxPos==idx) break;
            swap(idx,maxPos);
            idx = maxPos;
        }
    }

    private void swap(int idx, int idx2) {
        T tmp =nums[idx];
        nums[idx] = nums[idx2];
        nums[idx2] = tmp;
    }

    private void sort(){
        int n = size;
        while(n>0){
            swap(0,n-1);
            n--;
            heapify(0,n);

        }
    }
}
