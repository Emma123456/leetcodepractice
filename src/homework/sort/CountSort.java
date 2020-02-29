package homework.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountSort {
    /**
     * 计数排序，仅仅适用于比较值是int的类型
     * 时间复杂度O(n)，空间复杂度O(n+value) value=max-min+1
     * 是一个稳定排序
     * @param nums
     */
    public static void countSort(CountSortAble[] nums){
        int max = nums[0].getValue();
        int min = nums[0].getValue();
        for(int i=1;i<nums.length;i++){
            max = Math.max(max,nums[i].getValue());
            min = Math.min(min,nums[i].getValue());
        }

        //计数每个值有多少个元素
        int[] c = new int[max - min +1];
        for(int i = 0;i < nums.length;i++){
            c[nums[i].getValue() - min] ++;
        }

        //计数和累加
        for(int i=1;i<c.length;i++){
            c[i] += c[i-1];
        }

        CountSortAble[] r = new CountSortAble[nums.length];
        for(int i=nums.length-1;i>=0;i--){
            int idx = nums[i].getValue()-min;
            r[c[idx]-1] = nums[i];
            c[idx]--;
        }

        for(int  i=0;i<nums.length;i++){
            nums[i] = r[i];
        }
    }

    private static void printArray(Examinee[] nums){
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i].getName()+":"+nums[i].getScore());
            if(i!=nums.length-1){
                System.out.print(' ');
            }
        }
        System.out.println();
    }
    public static void main(String[] args){
        Examinee[] nums = new Examinee[8];
        nums[0] = new Examinee("张三",2);
        nums[1] = new Examinee("李四",5);
        nums[2] = new Examinee("王五",3);
        nums[3] = new Examinee("赵六",0);
        nums[4] = new Examinee("钱七",2);
        nums[5] = new Examinee("孙八",3);
        nums[6] = new Examinee("老九",0);
        nums[7] = new Examinee("老幺",3);

        countSort(nums);
        printArray(nums);

        Arrays.sort(nums);
    }
}
