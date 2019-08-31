package homework.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedList23 {

    /**
     * 用堆排序每个队列的头节点
     * 时间复杂度O(Nlongk)
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        PriorityQueue<ListNode> pqueue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            public int compare(ListNode node1,ListNode node2){
                return node1.val - node2.val;
            }
        });
        for(ListNode list : lists){
            if(list!=null)
                pqueue.offer(list);
        }
        while(!pqueue.isEmpty()){
            ListNode node = pqueue.poll();
            current.next = node;
            current = current.next;
            if(node.next!=null){
                pqueue.add(node.next);
            }

        }
        return dummy.next;
    }


    /**
     * 两个list合并，直到合并完成
     * 时间复杂度O(kN)
     * @param lists
     * @return
     */
    public ListNode mergeKListsV2(ListNode[] lists) {
        if(lists==null || lists.length==0) return null;
        ListNode list1 = lists[0];
        for(int i=1;i<lists.length;i++){
            list1 = merget2List(list1,lists[i]);
        }

        return list1;
    }

    private ListNode merget2List(ListNode node1,ListNode node2){
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while(node1!=null && node2!=null){
            if(node1.val<node2.val){
                current.next = node1;
                node1 = node1.next;
            }else{
                current.next = node2;
                node2 = node2.next;
            }
            current = current.next;
        }
        while(node1!=null){
            current.next = node1;
            node1 = node1.next;
            current = current.next;
        }
        while(node2!=null){
            current.next = node2;
            node2 = node2.next;
            current = current.next;
        }
        return dummy.next;
    }

    /**
     * 分治法，从代码来看和两两合并很类似，但是比较次数少了很多。
     * 时间复杂度O(Nlogk)
     * @param lists
     * @return
     */
    public ListNode mergeKListsV3(ListNode[] lists) {
        if(lists==null || lists.length==0) return null;
        int n= lists.length;
        int interval = 1;
        while(interval<n){
            for(int i=0;i<n-interval;i+=2*interval){
                lists[i] = merget2List(lists[i],lists[i+interval]);
            }
            interval = interval*2;
        }

        return lists[0];
    }



    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
