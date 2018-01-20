package array;

public class OneBitCharacter717 {
	public boolean isOneBitCharacter(int[] bits) {
		// 遍历
		return visit(bits, 0);
	}

	private boolean visit(int[] bits, int idx) {
		if (idx >= bits.length) {
			return false;
		}
		if (idx == bits.length - 1) {
			return bits[idx] == 0;
		} else {
			if (bits[idx] == 0) {// 单独成立
				return visit(bits, idx + 1);
			} else {// 需要与下一位联合起来才能有效
				return visit(bits, idx + 2);
			}
		}
	}

	/**
	 * 等价版本
	 * @param bits
	 * @return
	 */
	public boolean isOneBitCharacterV2(int[] bits) {
		int n = bits.length;
		int i = 0;
		while (i < n - 1) {
			if (bits[i] == 0)
				i++;
			else
				i += 2;
		}
		return i == n - 1;
	}

	public static void main(String[] args) {
		int[] bits = new int[] { 1, 0, 0 };
		boolean r = new OneBitCharacter717().isOneBitCharacter(bits);
		System.out.println(r);
	}

}
