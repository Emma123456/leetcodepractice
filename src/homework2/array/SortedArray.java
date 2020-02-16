package homework2.array;

/**
 * 一个大小固定的有序数组,支持新增、删除、查询操作
 */
public class SortedArray<T extends Comparable> {
    private T[] data;
    //容器大小
    private int n;
    //已经存放元素个数
    private int size;
    public SortedArray(int n){
        this.n = n;
        data = (T[]) new Comparable[n];
    }

    /**
     * 新增一个元素，当超出容器范围的时候抛出异常
     * @param t
     */
    public void add(T t){
        checkCapacity();
        int i = size -1;
        for(;i>0;i--){
            //从小到大排序
            if(data[i].compareTo(t)>0){
                data[i+1] =  data[i];
            }else{
                break;
            }
        }
        data[i+1] = t;
        size++;
    }

    /**
     * 返回元素个数
     * @return
     */
    public int getSize(){
        return this.size;
    }


    /**
     * 检查还有一个位置可以存放元素
     */
    private void checkCapacity() {

        if(size >= n){
            throw new RuntimeException("空间不足");
        }
    }

    /**
     * 返回位置index的元素
     * @param index
     * @return
     */
    public T get(int index){
        checkIndexRange(index);
        return data[index];
    }

    /**
     * 查找element在数组中的位置，如果不存在则返回-1
     * @param element
     * @return
     */
    public int find(T element){
        int index = -1;
        int start = 0,end = size-1;
        while(start <= end){
            int middle = (start + end) >> 1;
            if(data[middle].compareTo(element) == 0){
                return middle;
            }else if(data[middle].compareTo(element) >0){
                end = middle - 1;
            }else{
                start = middle + 1;
            }
        }
        return index;
    }

    /**
     * 检查下标index是一个合理的数值
     * @param index
     */
    private void checkIndexRange(int index) {
        if(index<0 || index>=size){
            throw  new IllegalArgumentException(index+"不是一个有效的索引位置");
        }
    }

    /**
     * 打印数组
     */
    public void print(){
        for(int i=0;i<size;i++){
            System.out.print(data[i] +"\t");
        }
    }

    /**
     * 删除并返回位置index的元素
     * @param index
     * @return
     */
    public T delete(int index){
        checkIndexRange(index);
        T  t = data[index];
        for(int i=index;i<size;i++){
            data[i] = data[i+1];
        }
        size -- ;
        return t;
    }


}
