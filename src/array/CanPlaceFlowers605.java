package array;

public class CanPlaceFlowers605 {
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		return visit(flowerbed, n, 0);
	}

	private boolean visit(int[] flowerbed, int n, int idx) {
		if (n == 0) {
			return true;
		}
		if (idx >= flowerbed.length) {
			return false;
		}
		if (flowerbed[idx] == 1 || (idx > 0 && flowerbed[idx - 1] == 1) || (idx + 1 < flowerbed.length && flowerbed[idx + 1] == 1)) {
			return visit(flowerbed, n, idx + 1);
		} else {
			flowerbed[idx] = 1;
			return visit(flowerbed, n - 1, idx + 1);
		}
	}

	/**
	 * 只检查与前一个数是否冲突
	 * 
	 * @param flowerbed
	 * @param n
	 * @return
	 */
	public boolean canPlaceFlowersV2(int[] flowerbed, int n) {
		for (int i = 0; i < flowerbed.length; i++) {
			if (n > 0 && flowerbed[i] == 0 && (i == 0 || (i > 0 && flowerbed[i - 1] == 0))) {
				flowerbed[i] = 1;
				n--;
			} else if (flowerbed[i] == 1 && i > 0 && flowerbed[i - 1] == 1) {
				// 因为入参不会包含冲突数据，所以冲突一定是因为种错了
				flowerbed[i - 1] = 0;
				n++;
			}
		}
		return n == 0;
	}

	/**
	 * 检查与前后数组的值是否冲突
	 * 
	 * @param flowerbed
	 * @param n
	 * @return
	 */
	public boolean canPlaceFlowersV3(int[] flowerbed, int n) {
		for (int i = 0; i < flowerbed.length && n > 0; i++) {
			if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i + 1 == flowerbed.length || flowerbed[i + 1] == 0)) {
				flowerbed[i] = 1;
				n--;
			}
		}
		return n == 0;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 0, 0, 1, 0, 0 };
		int n = 1;
		boolean r = new CanPlaceFlowers605().canPlaceFlowersV3(nums, n);
		System.out.println(r);
	}

}
