package array;

public class ContainerWithMostWater11 {
	/**
	 * 暴力
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			int min = height[i];
			for (int j = i + 1; j < height.length; j++) {
				min = Math.min(height[i], height[j]);
				maxArea = Math.max(maxArea, (j - i) * min);
			}
		}
		return maxArea;
	}

	public int maxAreaV2(int[] height) {
		int maxArea = 0;
		int left = 0, right = height.length - 1;
		while (left < right) {
			maxArea = Math.max(maxArea, (right-left)*Math.min(height[left], height[right]));
			if(height[left]<height[right]){
				left ++;
			}else{
				right -- ;
			}
		}
		return maxArea;
	}
	public static void main(String[] args) {

	}

}
