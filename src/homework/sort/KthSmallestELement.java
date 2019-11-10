package homework.sort;

/**
 * 查找一个数组中第k小元素
 */
public class KthSmallestELement {
    public static int kthSmallestElement(int[] nums,int k){
        int p = partion(nums,0,nums.length-1);
        while(p+1!=k){
            if(p+1<k){
                p = partion(nums,p+1,nums.length-1);
            }else{
                p = partion(nums,0,p-1);
            }
        }
        return nums[p];
    }

    private static int partion(int[] nums, int l, int r) {
        int i = l;
        int pivot = nums[r];
        for(int j=l;j<r;j++){
            if(nums[j]<=pivot){
                swap(nums,i,j);
                i++;
            }
        }
        swap(nums,i,r);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        if(i==j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args){
        int[] nums = new int[]{4,2,2,6,7,5};
        int k = 3;
        int v = kthSmallestElement(nums,k);
        System.out.println(v);
    }
}
