package book.heap;

public class HeapSortTest {
    public static void main(String[] args){
        Integer[] nums = new Integer[]{1,4,5,7,10};
        new HeapSort<Integer>().sort(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+"\t");
        }
        System.out.println();
    }
}
