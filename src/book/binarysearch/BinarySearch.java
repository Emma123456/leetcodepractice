package book.binarysearch;

public class BinarySearch {
    /**
     * 数组有序，不存在重复元素
     * 查找value所在位置，不存在则返回-1。
     * @param a
     * @param value
     * @return
     */
    public static int easySearch(int[] a,int value){
        if(a==null) return -1;
        int n = a.length;
        int low = 0;
        int high = n-1;
        return easySearch(a, value, low, high);

    }

    private static int easySearch(int[] a,int value,int low,int high){
        if(low>high) return -1;
        int mid = low + ((high-low)>>1);
        if(value==a[mid]) return mid;
        if(value<a[mid]) return easySearch(a,value,low,mid-1);
        return easySearch(a,value,mid+1,high);
    }

    /**
     * 查找数组a中第一个等于value的元素的位置
     * @param a
     * @param value
     * @return
     */
    public static int findFirstEqualElement(int[] a ,int value){
        if(a==null) return -1;
        int n = a.length;
        int low = 0;
        int high = n-1;
        while(low<=high){
            int mid = low + ((high-low)>>1);
            if(value<a[mid]){
                high = mid - 1;
            }else if(value>a[mid]){
                low = mid +1;
            }else{
                if(mid==0 || a[mid-1]!=value)
                    return mid;
                else
                    high = mid-1;
            }
        }
        return -1;
    }


    /**
     * 查找数组a中最后一个等于value的元素的位置
     * @param a
     * @param value
     * @return
     */
    public static int findLastEqualElement(int[] a ,int value){
        if(a==null) return -1;
        int n = a.length;
        int low = 0;
        int high = n-1;
        while(low<=high){
            int mid = low + ((high-low)>>1);
            if(value<a[mid]){
                high = mid - 1;
            }else if(value>a[mid]){
                low = mid +1;
            }else{
                if(mid==n-1 || a[mid+1]!=value)
                    return mid;
                else
                    high = mid-1;
            }
        }
        return -1;
    }


    /**
     * 查找数组a中第一个大于等于value的元素的位置
     * @param a
     * @param value
     * @return
     */
    public static int findFirstMoreOrEqualElement(int[] a ,int value){
        if(a==null) return -1;
        int n = a.length;
        int low = 0;
        int high = n-1;
        while(low<=high){
            int mid = low + ((high-low)>>1);
            if(value<=a[mid]){
                if(mid==0 || a[mid-1]<value) return mid;
                high = mid - 1;
            }else if(value>a[mid]){
                low = mid +1;
            }
        }
        return -1;
    }


    /**
     * 查找数组a中最后一个小于等于value的元素的位置
     * @param a
     * @param value
     * @return
     */
    public static int findLastLessOrEqualElement(int[] a ,int value){
        if(a==null) return -1;
        int n = a.length;
        int low = 0;
        int high = n-1;
        while(low<=high){
            int mid = low + ((high-low)>>1);
            if(value<a[mid]){
                high = mid - 1;
            }else if(value>=a[mid]){
                if(mid==n-1 || a[mid+1]>value)
                    return mid;
                low = mid +1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[][] testArray = new int[6][];
        testArray[0] = null;
        testArray[1] = new int[]{};
        testArray[2] = new int[]{1};
        testArray[3] =  new int[]{1,2};
        testArray[4] =new int[]{1,2,3};
        testArray[5] =  new int[]{1,2,3,4};

        for(int[] array : testArray){
            System.out.println("============");

            System.out.println(BinarySearch.easySearch(array,1));
            System.out.println(BinarySearch.easySearch(array,2));
            System.out.println(BinarySearch.easySearch(array,3));
            System.out.println(BinarySearch.easySearch(array,4));
        }

        System.out.println(BinarySearch.findLastLessOrEqualElement(new int[]{3,4,5,5,6,7,8,9},5));
    }
}
