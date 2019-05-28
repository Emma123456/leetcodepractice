package book.sort;

import java.util.Arrays;

public class Sorts {
    /**
     * 冒泡排序:1 每次比较相邻元素的大小；不符合要求，就交换位置；2 每次循环可以将一个元素放在最终位置，需要n次循环。
     * @param a
     *          数组
     */
    public static void bubbleSort(int[] a){
        int n = a.length;
        for(int i=0;i<n;i++){//n次循环
            boolean flag = false;
            for(int j=0;j<n-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag = true;//提前退出
                }
            }
            if(!flag) break;
        }
    }

    /**
     * 插入排序: 1 把数组分为排序好的部分和未排序的部分。从0到i-1是排序好的部分。2 每次考虑把a[i]插入哪个位置，类似于在数组中插入元素。所以最好情况是O(1)，最坏是O(n)。3 需要n次循环。
     * @param a
     */
    public static void insertionSort(int[] a){
        int n = a.length;
        for(int i=1;i<n;i++){
            int val = a[i];
            int j=i-1;
            //查找要插入的位置，并且挪动元素
            for(;j>=0;j--){
                if(a[j]>val){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = val;
        }
    }

    /**
     * 选择排序：1 把数组分为排序好的部分和未排序的部分。2 每次从未排序的部分选择最小的元素，添加到排序好的数组的末尾。要查找到最小元素，每次都需要n次比较，所以是O(n)。 3 需要n次循环。
     * @param a
     */
    public static void selectionSort(int[] a){

        int n = a.length;
        for(int i=0;i<n;i++){
            int minValueIndex = i;
            for(int j=i;j<n;j++){
                if(a[j]<a[minValueIndex]){
                    minValueIndex=j;
                }
            }
            if(minValueIndex!=i){
                int temp = a[i];
                a[i]=a[minValueIndex];
                a[minValueIndex] = temp;
            }

        }
    }


    public static void shellSort(int[] a) {

    }

    /**
     * 归并排序使用分治思想。如果数组0到i，i+1到n-1两个子数组是排序好的，则需要合并两个部分，最终就得到排序好的数组。
     * 数组i到j的排序问题缩小为数组i到p和数组p+1到j两个数组的排序问题，这是不断递进。两个排序好的子数组合并起来，就是将子问题的结果回归到原来的问题。
     * 不断递进的退出条件是i>=j。
     * 分治法是算法；递归是编程技巧。
     * @param a
     */
    public  static void mergeSort(int[] a){
        int n = a.length;
        mergeSort(a,0,n-1);
    }

    /**
     * 左右包含
     * @param a
     * @param l
     * @param r
     */
    private static void mergeSort(int[] a, int l, int r) {
        if(l>=r) return;
        int mid = l+(r-l)/2;
        mergeSort(a,l,mid);
        mergeSort(a,mid+1,r);
        merge(a,l,mid,r);
    }

    /**
     * 合并排序好的两个部分
     * @param a
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(int[] a, int l, int mid, int r) {
        int[] values = new int[r-l+1];
        int i=l;
        int j=mid+1;
        int index = 0;
        while(i<=mid && j<=r){
            if(a[i]<a[j]){
                values[index] = a[i];
                i++;
            }else{
                values[index] = a[j];
                j++;
            }
            index++;
        }
        while(i<=mid){
            values[index++] = a[i++];
        }
        while(j<=r){
            values[index++] = a[j++];
        }

        for(i=0;i<=r-l;i++){
            a[l+i] = values[i];
        }
    }


    /**
     * 快速排序同样使用分治法。1 排序i到j数组，选择a[j]作为pivot，假设排序好后a[j]的位置是p,从i到p之间的元素是<=pivot的，从p+1到j之间的元素是>pivot的.
     * 2 递归排序i到p，p+1到j
     * 3 递归退出条件是i>=j
     * @param a
     */
    public static void quickSort(int[] a){
        quickSort(a,0,a.length-1);
    }

    private static void quickSort(int[] a, int left, int right) {
        if(left>=right) return;
        int q = partition(a,left,right);
        quickSort(a,left,q-1);
        quickSort(a,q+1,right);


    }

    /**
     * 类似于选择排序。假设数组从left到i-1是已经分好的，是<pivot的。当遇到一个新的<pivot的数值，追加在i的位置。
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] a , int left,int right){
        int pivot = a[right];
        int i = left;
        for(int j=left;j<right;j++){
            if(a[j]<pivot){
                if(i!=j){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
                i++;
            }
        }
        a[right]=a[i];
        a[i] = pivot;
        return i;
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
            Sorts.quickSort(a);
            System.out.println(Arrays.toString(a));
        }

    }
}
