package homework2;

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
        int pivot = nums[r];
        int i = l,j=r-1;
        while(i<r){
            while(j>l && nums[j]>pivot) j--;
            while(i<r && nums[i]<=pivot) i++;
            if(i<j){
                swap(nums,i,j);
            }else{
                break;
            }
        }
        swap(nums,i,r);
        return i;
    }

    private static void swap(int[] nums ,int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public static void main(String[] args){
        int[] nums = new int[]{4,2,2,6,7,5};
        int k = 3;
        int v = kthSmallestElement(nums,k);
        System.out.println(v);


    }
}
