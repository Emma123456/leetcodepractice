package book.sort;

import java.util.Random;

public class InsertionBubbleTest {
    public static void main(String[] args){
        int m = 10000;
        int n = 200;
        int[][] nums = new int[m][n];
        Random random = new Random();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                nums[i][j] = random.nextInt(100);
            }
        }

        long start = System.currentTimeMillis();
        for(int i=0;i<m;i++){
            Sorts.bubbleSort(nums[i]);
        }

        long end = System.currentTimeMillis();

        System.out.println("冒泡排序 "+(end-start));

        start = System.currentTimeMillis();
        for(int i=0;i<m;i++){
            Sorts.insertionSort(nums[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("插入排序 "+(end-start));
    }
}
