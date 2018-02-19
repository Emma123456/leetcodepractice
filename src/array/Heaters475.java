package array;

import java.util.Arrays;

public class Heaters475 {
	/**
	 * 二分查找距离house最近的发光源
	 * @param houses
	 * @param heaters
	 * @return
	 */
	public int findRadius(int[] houses, int[] heaters) {
		int radius = 0;
		Arrays.sort(heaters);
		for (int house : houses) {
			int left = 0, right = heaters.length;
			int dis = Integer.MAX_VALUE;
			while (left < right) {
				int middle = (left + right) / 2;
				dis = Math.min(dis,Math.abs(heaters[middle]-house));
				if (heaters[middle] == house) {
					break;
				} else if (heaters[middle] > house) {
					right = middle;
				} else {
					left = middle+1;
				}
			}
			radius = Math.max(radius, dis);
		}
		return radius;
	}

	/**
	 * 好聪明的做法
	 * @param houses
	 * @param heaters
	 * @return
	 */
	public int findRadiusV2(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		Arrays.sort(houses);
		int radius = 0;
		int i =0 ;
		for(int house: houses){
			while(i<heaters.length-1 && heaters[i] +heaters[i+1]<=house*2){
				i++;
			}
			radius = Math.max(radius, Math.abs(house-heaters[i]));
		}
		return radius;
	}
	public static void main(String[] args) {
		int[] h = new int[] { 282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923};
		int[] heaters = new int[] { 823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612 };
		int t = new Heaters475().findRadiusV2(h, heaters);
		System.out.println(t);
	}
}
