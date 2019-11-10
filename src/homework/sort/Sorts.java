package homework.sort;

import java.util.Random;

public class Sorts {
    /**
     * 冒泡排序
     * 比较相邻的两个元素
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++) {
            boolean swap = false;
            for(int j=1;j<n;j++) {
                if(nums[j]<nums[j-1]) {
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                    swap = true;
                }
            }
            if(!swap) break;
        }
    }

    /**
     * 插入排序
     * 在一个有数组的末尾插入一个元素，数组分有序区间和无序区间
     * @param nums
     */
    public static void insertionSort(int[] nums) {
        int n = nums.length;
        for(int i=1;i<n;i++) {
            //有序区间是[0,i-1]
            int temp = nums[i];
            int j=i-1;
            for(;j>=0;j--) {
                if(nums[j]>temp) {
                    nums[j+1]=nums[j];
                }else {
                    break;
                }
            }
            nums[j+1] = temp;
        }
    }

    /**
     * 选择排序
     * 从未排序的部分选择一个最小的数据，填充到已经排序的数组末尾
     */
    public static void selectionSort(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(nums[i]>nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * 归并排序，先不断递归，排序好左右两边的数组，再合并两个有序数组
     * @param nums
     */
    public static void mergeSort(int[] nums){

        mergeSort(nums,0,nums.length-1);
    }

    private static void mergeSort(int[] nums, int start, int end) {
        if(start >= end){
            return;
        }
        int middle = start+(end-start)/2;
        mergeSort(nums,start,middle);
        mergeSort(nums,middle+1,end);
        merge(nums,start,middle,end);
    }

    /**
     * 合并
     * @param nums
     * @param start
     * @param middle
     * @param end
     */
    private static void merge(int[] nums, int start, int middle, int end) {

        int len = (middle - start+1)*2;
        int[] data = new int[len];
        int idx = 0;
        int i = start,j=middle+1;
        while (i<=middle && j<=end){
            if(nums[i]<nums[j]){
                data[idx++] = nums[i++];
            }else{
                data[idx++] = nums[j++];
            }
        }

        while (i<=middle){
            data[idx++] = nums[i++];
        }
        while(j<=end){
            data[idx++] = nums[j++];
        }

        for(i=0;i<idx;i++){
            nums[start+i] = data[i];
        }
    }

    /**
     * 快速排序，分治法
     * 1 先选择一个pivote
     * 2 < p 在左边，>p在后边
     * 3 终止条件是只有一个元素
     * @param nums
     */
    public static void quickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if(start >= end){
            return;
        }
        int q = partition(nums,start,end);
        quickSort(nums,start,q-1);
        quickSort(nums,q+1,end);
    }

    /**
     * 分区，返回分区点所在下标
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] nums, int start, int end) {
        int[] values = selectPivote(nums,start,end);
        int pivote = values[0];
        int pivoteIdx = values[1];
        swap(nums,start,pivoteIdx);
        int i = start,j=end;
        while(i<j){
            while(j>i && nums[j]>pivote) j--;
            while(i<j && nums[i]<=pivote) i++;
            if(i<j){
                swap(nums,i,j);
            }
        }
        swap(nums,start,j);

        return j;
    }

    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //在LeetCode测试，随机要比选择start快3秒
    private static int[] selectPivote(int[] nums, int start, int end) {
        int idx = new Random().nextInt(end-start+1)+start;
        return new int[]{nums[idx],idx};
    }


    /**
     * 计数排序
      */
    public static int[] countSort(int[] nums){
        if(nums.length == 0) return new int[]{};
        //存储每个分数的考生在数组中的位置
        int[] r = new int[nums.length];

        //找到数值范围
        int max =  nums[0];
        int min = nums[0];
        for(int i=1;i<nums.length;i++){
            max = Math.max(max,nums[i]);
            min = Math.min(min,nums[i]);
        }

        //存储values值=i的数据个数
        int[] c = new int[max-min+1];
        for(int i=0;i<nums.length;i++){
            c[nums[i]]++;
        }

        for(int i=1;i<c.length;i++){
            c[i] += c[i-1];
        }

        for(int i=nums.length-1;i>=0;i--){
            r[c[nums[i]]-1] = nums[i];
            c[nums[i]]--;
        }
        return r;
    }

    private static void print(int[] nums) {
        System.out.println();
        for(int n : nums) {
            System.out.print(n+"\t");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] nums = new int[] {3,4,4,2};
        quickSort(nums);
        print(nums);

        int[] r = countSort(new int[]{2,5,3,0,2,3,0,3});
        print(r);

    }
}
