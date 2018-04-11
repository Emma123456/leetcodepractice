package array;

public class TransformToChessboard782 {
	public int movesToChessboard(int[][] b) {
		int swapCount = 0;
		int n = b.length;
		for (int i = 1; i < n; i++) {
			boolean same = true;
			for(int j = 0;j<n;j++){
				if(b[i][j] != b[i-1][j]){
					same = false;
					break;
				}
			}
			if(same){
				
			}
		}
		return swapCount;
	}

	public int movesToChessboardV2(int[][] b) {
		int N = b.length, rowSum = 0, colSum = 0, rowSwap = 0, colSwap = 0;
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				if ((b[0][0] ^ b[i][0] ^ b[0][j] ^ b[i][j]) == 1)
					return -1;
		for (int i = 0; i < N; ++i) {
			rowSum += b[0][i];
			colSum += b[i][0];
			if (b[i][0] == i % 2)
				rowSwap++;
			if (b[0][i] == i % 2)
				colSwap++;
		}
		if (N / 2 > rowSum || rowSum > (N + 1) / 2)
			return -1;
		if (N / 2 > colSum || colSum > (N + 1) / 2)
			return -1;
		if (N % 2 == 1) {
			if (colSwap % 2 == 1)
				colSwap = N - colSwap;
			if (rowSwap % 2 == 1)
				rowSwap = N - rowSwap;
		} else {
			colSwap = Math.min(N - colSwap, colSwap);
			rowSwap = Math.min(N - rowSwap, rowSwap);
		}
		return (colSwap + rowSwap) / 2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
