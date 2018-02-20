package array;

public class PlusOne66 {
	/**
	 * 思路：从低位开始加，如果不发生进位，则加法结束。如果发生进位，则前进到前一位做加1操作。最后判断，如果digits[0]=0则说明存在进位，需要扩展数组。
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		int i = n - 1;
		while (i >= 0) {
			if (digits[i] < 9) {
				digits[i] += 1;
				break;
			} else {
				digits[i] = 0;
				i--;
			}
		}
		if (digits[0] == 0) {
			int[] r = new int[n + 1];
			System.arraycopy(digits, 0, r, 1, n);
			r[0] = 1;
			return r;
		}
		return digits;
	}

	/**
	 * 思路跟上面差不多。不同之处是：加法结束直接返回；如果所有位数遍历完，还没有返回，则说明是 999+1，则需要扩展数组，将首位修改为1。
	 * @param digits
	 * @return
	 */
	public int[] plusOneV2(int[] digits) {
		int n = digits.length;
		int i = n - 1;
		while (i >= 0) {
			if (digits[i] < 9) {
				digits[i] += 1;
				return digits;
			} else {
				digits[i] = 0;
				i--;
			}
		}
		int[] r = new int[n + 1];
		r[0] = 1;
		return r;
	}
}
