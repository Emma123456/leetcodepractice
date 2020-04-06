package homework2.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 为动态数据集查找最大的K个元素
 * 线程不安全
 */
public class MaxTopK {
    private int k;
    private Heap2<Integer> heap;
    public MaxTopK(int k){
        this.k = k;
        heap = new Heap2<Integer>(k);
    }

    /**
     * 插入元素
     * @param data
     */
    public void insert(int data){
        if(heap.size() < k ){
            heap.insert(data);
        }else if(data > heap.getTop()){
            heap.remove();
            heap.insert(data);
        }
    }

    /**
     * 返回前k大元素
     * @return
     */
    public List<Integer> top(){
        List<Integer> result = new ArrayList<>();
        while(heap != null && !heap.isEmpty()){
            result.add(heap.remove());
        }
        if(! result.isEmpty()){
            for(Integer data : result){
                heap.insert(data);
            }
        }
        return result;
    }
}
