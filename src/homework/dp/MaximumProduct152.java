package homework.dp;

public class MaximumProduct152 {
    /**
     * 计算子数组的乘积。如果以i为起点，那么下标[i,i+1]是一个子数组，[i,i+2]也是一个子数组，...一直到[i,n-1]。是所有以i为起点的子数组。
     * @param nums
     * @return
     */
    public int maxProductV1(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                //[i,i+1],[i,i+2]...[i,n]
                int product = 1;
                for(int k =i;k<=j;k++){
                    product = product*nums[k];
                }
                max = Math.max(max,product);
            }
        }

        return max;
    }

    /***
     * 上面计算乘积有重复的计算。例如子数组[i,i+2]的乘积，是P([i,i+1])*nums[i+2]即可。
     * @param nums
     * @return
     */
    public int maxProductV2(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int product = 1;
            for(int j=i;j<n;j++){
                product = product*nums[j];
                max = Math.max(max,product);
            }
        }
        return max;
    }


    /**
     * 使用分步计算的思想。
     * 对于nums[0]肯定必须使用，因为至少有一个元素。
     * 在nums[0]的，基础上，最大乘积子数组要不要加nums[1]这个元素。
     * 那需要计算：nums[0],nums[0]*nums[1],nums[1]，选择一个最大值。
     * 那接着继续思考要不要加nums[2]这个元素：那就计算一下：nums[2],nums[2]*(以nums[1]为结尾的最大乘积)，因为子数组必须连续，以nums[0]为结尾，nums[1]不参加的子数组，不能加入
     * nums[2]这个元素。
     * [2,3,-2,4]
     * 使用2   max_product=2
     * 考虑元素3：
     *      不使用3，那么max_product保持原始值=2
     *      单独使用3，  max_product=3
     *      追加在上一个元素后面，max_product=2*3=6
     *
     * 考虑元素-2
     *      不实用-2，那么max_product=2，3，6
     *      单独使用-2，那么max_product=-2；
     *      追加在上一个元素后面，那么max_product= 3*-2=-6
     *                                          6*-2=-12
     *
     * 考虑元素4
     *      不使用4，那么max_product=2，3，6，-6
     *      单独使用4，那么max_product=4
     *      追加在上一个元素后面，那么max_product= （-2，-6，-12）分别乘以4
     *
     * 最后在所有max_product的可能值中选择最大的值6，作为最终答案。
     *
     * 可以观察到在每一步计算"追加在上一个元素后面"这个可能选项的时候与以上一个元素为结尾的子数组乘积有关。
     * 而以上一个元素为结尾的子数组乘积可能有很多个，可以选择用List保存。但进一步思考，我们考察的是最大乘积，
     * 那是不是只要把以上一个元素为结尾的子数组的最大乘积保留下来就可以了？
     * 考虑到数组中的值可能是负数，负数和负数相城得到的正数有可能是最大乘积，所以同时还需要把以上一个元素为结尾的子数组的最小乘积保留下来。
     * 数组maxProduct[i]表示以元素nums[i]为结尾的子数组的最大乘积
     * minProduct[i]表示以元素nums[i]为结尾的子数组的最小乘积。
     * maxProduct[i]=max(nums[i],nums[i]*maxProduct[i-1],nums[i]*minProduct[i-1])
     * 记录最小值也同理：minProduct[i]=max(nums[i],nums[i]*minProduct[i-1],nums[i]*minProduct[i-1])
     * 最后在minProduct中选择最大值返回。
     * @param nums
     * @return
     */
    public int maxProductV3(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int n = nums.length;
        int[] maxProduct = new int[n];
        int[] minProduct = new int[n];
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        int max = maxProduct[0];
        for(int i=1;i<n;i++){
            maxProduct[i] = Math.max(nums[i],nums[i]*maxProduct[i-1]);
            maxProduct[i] = Math.max(maxProduct[i],nums[i]*minProduct[i-1]);
            max = Math.max(max,maxProduct[i]);

            minProduct[i] = Math.min(nums[i],nums[i]*minProduct[i-1]);
            minProduct[i] = Math.min(minProduct[i],nums[i]*maxProduct[i-1]);
        }
        return max;
    }


    /**
     * 对上一步做空间优化，如果不是一步一步过来，还真看不懂这个解法
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int n = nums.length;
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for(int i=1;i<n;i++){
            int maxtmp = max;
            max = Math.max(nums[i],nums[i]*max);
            max = Math.max(max,nums[i]*min);

            min = Math.min(nums[i],nums[i]*min);
            min = Math.min(min,nums[i]*maxtmp);
            res = Math.max(max,res);
        }
        return res;
    }
}
