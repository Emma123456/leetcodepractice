package array;

public class GlobalLocalInversions775 {
	public boolean isIdealPermutation(int[] A) {
		int localCount = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] > A[i + 1]) {
				localCount++;
			}
		}
		int globalCount = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[i] > A[j]) {
					globalCount++;
				}
			}
		}
		return localCount == globalCount;
	}
	/**
	 * 忽略了一个重要条件 A of [0, 1, ..., N - 1] 
	 * 1 每个local inversion 也是一个global inversion
	 * 2 如果想要 localCount = globalCount，也就是说只有local inversion，没有其余的global inversion
	 * 3 如果只有local inversion，那么当遇到inversion的时候，只要交换A[i]和A[i+1]的位置，那么数组从0到i+1就有序了。
	 * @param A
	 * @return
	 */
	public boolean isIdealPermutationV2(int[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] > A[i]) {
				int tmp = A[i-1];
				A[i-1] = A[i];
				A[i] = tmp;
				if(A[i-1]!=i-1 || A[i]!=i) return false;
			}
		}
		return true;
	}
	/**
	 * 再进一步，如果A[i-1] == A[i]+1，那么交换之后数组排序正确，那这就是一次local inversion。
	 * 如果不是这种情况，并且A[i-1]不是正确的数字，那么A[i-1]与A[i]之间相差就不是1，就会产生global inversion。
	 * @param A
	 * @return
	 */
	public boolean isIdealPermutationV3(int[] A) {
		for (int i = 1; i < A.length; i++) {
			if(A[i-1] == A[i]+1){
				int tmp = A[i-1];
				A[i-1] = A[i];
				A[i] = tmp;
			}else if(A[i-1]!=i-1){//A[i-1]=i-1是一个正确的顺序
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		int[] A = new int[]{2,0,1};
		boolean r = new GlobalLocalInversions775().isIdealPermutationV2(A);
		System.out.println(r);
	}
}
