package homework2.array;

public class SortedArrayMerge {

    public static int[] merge(int[] array1 , int[] array2){
        array1 = array1==null?new int[]{}:array1;
        array2 = array2 ==null?new int[]{}:array2;
        int[] result = new int[array1.length + array2.length];
        int index = 0;
        int i=0,j=0;
        while(i< array1.length && j<array2.length){
            if(array1[i] > array2[j]){
                result[index++] = array2[j++];
            }else{
                result[index++] = array1[i++];
            }
        }

        while(i< array1.length){
            result[index++] = array1[i++];
        }
        while(j < array2.length){
            result[index++] = array2[j++];
        }
        return result;
    }

    public static void main(String[] args){

        int[] newData = merge(new int[]{1,8,40,69},new int[]{3,10,56,78,78,78,90});
        for(int val : newData){
            System.out.print(val+"\t");
        }
        System.out.println();
    }
}
