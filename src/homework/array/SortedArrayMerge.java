package homework.array;

public class SortedArrayMerge {
    /**
     * 合并两个有序的数组
     * @param array1
     * @param array2
     * @return
     */
    public static int[] merge(SortedArray<Integer> array1, SortedArray<Integer> array2){
        int[] newData = new int[array1.getSize()+array2.getSize()];
        int i=0,j=0,idx=0;
        while(i<array1.getSize() && j<array2.getSize()){
            if(array1.get(i).compareTo(array2.get(j))<0){
                newData[idx] = array1.get(i);
                i++;
            }else{
                newData[idx] = array2.get(j);
                j++;
            }
            idx++;
        }

        while(i<array1.getSize()){
            newData[idx++] = array1.get(i++);
        }
        while(j<array2.getSize()){
            newData[idx++] = array2.get(j++);
        }
        return newData;
    }

    public static void main(String[] args){
        SortedArray array = new SortedArray<Integer>();
        array.add(1);
        array.add(10);
        array.add(8);
        array.add(100);
        array.add(19);


        SortedArray array2 = new SortedArray<Integer>(15);
        array2.add(5);
        array2.add(8);
        array2.add(8);
        array2.add(7);
        array2.add(309);
        for(int i=0;i<10;i++){
            array2.add(i);
        }

        int[] newData = merge(array,array2);
        for(int val : newData){
            System.out.print(val+"\t");
        }
        System.out.println();
    }
}
