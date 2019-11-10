package homework.tree;

import java.util.Arrays;

public class HeapSort {
    /**
     * 堆排序
     * 先构建一个大顶堆
     * @param data
     */
    public static void heapSort(int[] data){
        int n = data.length;
        for(int i=n/2;i>=0;i--){
            heapify(data,n,i);
        }
        int len = n;
        for(int i=n-1;i>=0;i--){
            swap(data,i,0);
            len--;
            heapify(data,len,0);
        }
    }


    private static void heapify(int[] data,int  n,int k){
        while (true){
            int maxPos = k;
            int leftIdx = (k<<1)+1;
            int righIdx = leftIdx+1;
            if(leftIdx<n && data[k]<data[leftIdx]){
                maxPos = leftIdx;
            }
            if(righIdx<n && data[maxPos]<data[righIdx]){
                maxPos = righIdx;
            }
            if(maxPos==k){
                break;
            }
            swap(data,k,maxPos);
            k = maxPos;
        }

    }

    private static  void swap(int[] data,int i,int j){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    public static void main(String[] args){
        int[][] testData = new int[8][];
        testData[0]=new int[]{};
        testData[1]= new int[]{1};
        testData[2]= new int[]{1,2};
        testData[3]= new int[]{2,1};
        testData[4]= new int[]{1,2,3};
        testData[5]= new int[]{3,1,2};
        testData[6]= new int[]{3,4,2,1};
        testData[7]= new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        for(int[] a : testData){
            HeapSort.heapSort(a);
            System.out.println(Arrays.toString(a));
        }
    }
}
