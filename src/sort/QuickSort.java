package sort;

public class QuickSort {
	public static void quickSort(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
	}

	private static void quickSort(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}

		int pivot = nums[start];
		int i = start;//细节1
		int j = end;
		while (i < j) {
			//细节2，先做j的减操作
			while (j > i && nums[j] > pivot) {
				j--;
			}
			//细节3 <=
			while (i < j && nums[i] <= pivot) {
				i++;
			}
			if(i<j){
				swap(nums, i, j);
			}
		}
		swap(nums, start, i);
		quickSort(nums, start, i - 1);
		quickSort(nums, i + 1, end);
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{5 ,2 ,8, 9 ,2, 3, 4 ,9};
		quickSort(nums);
		for(int num : nums){
			System.out.print(num+"\t");
		}
	}

}
