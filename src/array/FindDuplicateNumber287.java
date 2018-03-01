package array;

public class FindDuplicateNumber287 {
	/**
	 * Cycle detection 1 Floyd's Tortoise and Hare 2 Brent's algorithm 3
	 * Gosper's algorithm
	 * 
	 * @param nums
	 * @return
	 */
	public int findDuplicate(int[] nums) {
		int slow = 0, fast = 0;
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);
		slow = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}

	/**
	 * 只有一个数字重复
	 * nums中每个数值都在[1,n]。如果nums中的<=mid的数比mid多，则多出的数字一定在[1,mid-1]之间.
	 * 例如n=10，mid=5，<=5的数字有5个，那重复的数字应该在[6,10]之间；<=5的数字有4个，那重复的数字应该在[6,10]之间；如果<=5的数字有6个，那重复的数字一定在[1,5]之间。
	 * @param nums
	 * @return
	 */
	public int findDuplicateV2(int[] nums) {
		int low = 1,high= nums.length-1;
        while(low<high){
            int mid = (high+low)/2;
            int count = 0;
            for(int num :nums){
                if(num<=mid) count++;
            }
            if(count<=mid) low = mid+1;
            else high =mid;
        }
        return low;
	}

	public static void main(String[] args) {

	}

}
