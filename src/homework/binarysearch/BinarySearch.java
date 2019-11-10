package homework.binarysearch;

public class BinarySearch {
    /**
     * 不存在重复元素，在有序数组中查找value。存在则返回下标，否则返回-1
     * @param a
     * @param value
     * @return
     */
    public static int easySearch(int[] a,int value){
        if(a==null || a.length==0) return -1;
        int start = 0;
        int end = a.length-1;
        while(start<=end){
            int middle = start+((end-start)>>1);
            if(a[middle]==value) return middle;
            if(a[middle]<value){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }
        return -1;
    }


    /**
     * 在有序数组中查找第一个值等于value的元素下标
     * @param a
     * @param value
     * @return
     */
    public static int findFirstEqualElement(int[] a ,int value){
        if(a==null || a.length==0) return -1;
        int start = 0;
        int end = a.length-1;
        while(start<=end){
            int middle = start+((end-start)>>1);
            if(a[middle]==value ) {
                if(middle==0 || a[middle-1]!=value){
                    return middle;
                }else{
                    end = middle -1;
                }
            }
            if(a[middle]<value){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }
        return -1;
    }


    /**
     * 在有序数组中查找最后一个值等于value的元素下标
     * @param a
     * @param value
     * @return
     */
    public static int findLastEqualElement(int[] a ,int value){

        if(a==null || a.length==0) return -1;
        int start = 0;
        int end = a.length-1;
        while(start<=end){
            int middle = start+((end-start)>>1);
            if(a[middle]==value ) {
                if(middle==a.length-1 || a[middle+1]!=value){
                    return middle;
                }else{
                    start = middle+1;
                }

            }
            if(a[middle]<value){
                start = middle+1;
            }else{
                end = middle-1;
            }
        }
        return -1;
    }


    /**
     * 在有序数组中查找第一个大于等于value的元素下标
     * @param a
     * @param value
     * @return
     */
    public static int findFirstMoreOrEqualElement(int[] a ,int value){
        if(a==null || a.length==0) return -1;
        int start = 0;
        int end = a.length-1;
        while(start<=end){
            int middle = start+((end-start)>>1);
            if(a[middle]<value){
                start = middle+1;
            }else{
                if(middle==0 || a[middle]<value) return middle;
                end = middle-1;
            }
        }
        return -1;
    }

    /**
     * 在有序数组中查找最后一个小于等于value的元素下标
     * @param a
     * @param value
     * @return
     */
    public static int findLastLessOrEqualElement(int[] a ,int value){
        if(a==null || a.length==0) return -1;
        int start = 0;
        int end = a.length-1;
        while(start<=end){
            int middle = start+((end-start)>>1);
            if(a[middle]>value){
                end = middle-1;
            }else{
                if(middle == a.length-1 || a[middle+1]>value) return middle;
                start = middle+1;
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

        System.out.println(BinarySearch.findFirstMoreOrEqualElement(new int[]{3,4,5,5,6,7,8,9},10));



        int a = 2123366400;
    }
}
