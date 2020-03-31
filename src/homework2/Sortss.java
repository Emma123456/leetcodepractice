package homework2;

import java.util.Random;

public class Sortss {
    public static void bubbleSort(int[] nums){
        int n = nums.length;
        for(int i=0;i<n;i++){
            for(int j=1;j<n;j++){
                if(nums[j]<nums[j-1]){
                    int tmp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = tmp;
                }
            }
        }
    }

    /**
     * 插入排序，在一个有序数组中找一个位置插入元素
     * @param nums
     */
    public static void insertionSort(int[] nums){
        int n = nums.length;
        for(int i = 1;i<n;i++){
            int tmp  = nums[i];
            int idx = i;
            for(int j= i -1;j>=0;j--){
                if(nums[j]>tmp){
                    nums[j+1] = nums[j];
                }else{
                    idx = j+1;
                    break;
                }
            }
            nums[idx] = tmp;
        }
    }

    /**
     * 选择排序:从未排序的数组中选择最小的元素，插入到已排序数组的末尾
     * @param nums
     */
    public static void selectionSort(int[] nums){
        int n = nums.length;
        for(int i=0;i<n;i++){
            int minValueIndex = i;
            for(int j = i+1;j<n;j++){
                if(nums[j] < nums[minValueIndex]){
                    minValueIndex = j;
                }
            }
            if(minValueIndex != i){
                int tmp = nums[i];
                nums[i] = nums[minValueIndex];
                nums[minValueIndex] = tmp;
            }
        }
    }


    /**
     * 归并排序
     * @param nums
     */
    public static void mergeSort(int[] nums){

        mergeSort(nums,0,nums.length-1);
    }

    private static void mergeSort(int[] nums, int p, int q) {
        if(p<q){
            int i = (p+q)/2;
            mergeSort(nums,p,i);
            mergeSort(nums,i+1,q);
            mergeSortedArray(nums,p,i,q);
        }
    }

    /**
     * 数组从p到i有序，从i+1到q有序，合并为一个有序数组
     * @param nums
     * @param p
     * @param i
     * @param q
     */
    private static void mergeSortedArray(int[] nums, int p, int i, int q) {
        int[] arrayas = new int[q-p+1];
        int ii = p,jj = i+1,index = 0;
        while(ii<=i && jj<=q){
            if(nums[jj]<nums[ii]){
                arrayas[index++] = nums[jj++];
            }else{
                arrayas[index++] = nums[ii++];
            }
        }
        while(ii<=i){
            arrayas[index++] = nums[ii++];
        }
        while(jj<=q){
            arrayas[index++] = nums[jj++];
        }

        System.arraycopy(arrayas,0,nums,p,arrayas.length);
    }

    /**
     * 快排  先选择一个元素作为pivot，数组左边是＜pivot的元素，右边是＞pviot的元素
     * @param nums
     */
    public static void quickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if(start>=end)  return;
        int q = partition(nums,start,end);
        quickSort(nums,start,q-1);
        quickSort(nums,q+1,end);

    }

    public Sortss() {
        super();
    }

    private static int partition(int[] nums, int start, int end) {
        int idx = new Random().nextInt(end-start+1) + start;
        int pivot = nums[idx];
        int l = start , r = end-1;
        //先将pivot元素放到最后一位，不参与分组
        swap(nums,end,idx);
        while(l<r){
            //指针 左移，发现nums[r]<pivot
            while(r>start && nums[r] > pivot ) r--;
            //指针右移，发现nums[l]>pivot
            while(l<end && nums[l] <= pivot ) l++;
            if(l<r){
                //交换元素
                swap(nums,l,r);
            }else{
                break;
            }
        }
        //l指向的是pivot元素所在位置，交换
        swap(nums,end,l);
        return l;
    }

    private static void swap(int[] nums ,int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args){
        int[] nums = new int[]{10,4,1,5,8,6};
        quickSort(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+",");
        }
    }
}
