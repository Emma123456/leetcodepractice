package linkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists23 {
	/**
	 * 思路1：把所有数据放在一个List，然后排序list。最后再创建ListNode。
	 * 思路2：一个一个比较。时间复杂度O(kn)；空间O(n)
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode mergeHead = new ListNode(0);
		ListNode node = mergeHead;
		while(true){
			ListNode minHead = null;
            int idx = -1;
			for (int i=0;i<lists.length;i++) {
				if (lists[i] != null) {
					if(minHead == null || minHead.val> lists[i].val){
						minHead = lists[i];
                        idx = i;
					}
				}
			}
			if(minHead==null){
				break;
			}
            lists[idx] = lists[idx].next;
			node.next = new ListNode(minHead.val);
			node = node.next;
		}
		return mergeHead.next;
    }
	
	/**
	 * 用优先队列解决上面
	 * Time complexity : O(Nlogk) 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsV2(ListNode[] lists) {
		if(lists==null || lists.length==0) return null;
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		for(ListNode node : lists){
			if(node!=null){
				heap.offer(node);
			}
		}
		ListNode mergeHead = new ListNode(0);
		ListNode node = mergeHead;
		while(!heap.isEmpty()){
			ListNode minNode = heap.poll();
			node.next = minNode;
			node = node.next;
			if(minNode.next!=null){
				heap.offer(minNode.next);
			}
		}
		return mergeHead.next;
	}
	
	/**
	 * 转换成merge 2 sorted List的问题
	 * 合并k-1次
	 * 225 ms	
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsV3(ListNode[] lists) {
		 if(lists==null || lists.length==0) return null;
		int idx = 1;
		ListNode result = lists[0];
		while(idx<lists.length){
			result = mergeTwoListsV2(result,lists[idx]);
			idx++;
		}
		return result;
	}
	
	public ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
		if(l1==null ||  l2==null){
			return l1==null?l2:l1;
		}
		ListNode result = new ListNode(0);
		ListNode preNode = result ;
		while(l1!=null && l2!=null){
			if(l1.val<=l2.val){
				preNode.next = l1;
				l1 = l1.next;
			}else{
				preNode.next = l2;
				l2= l2.next;
			}
			preNode = preNode.next;
		}
		if(l1!=null){
			preNode.next = l1;
		}
		if(l2!=null){
			preNode.next = l2;
		}
		return result.next;
	}

	/**
	 * 用分治法 divide and conquer
	 * 合并 log2 ^k次
	 * 14 ms	
	 * @param lists
	 * @return
	 */
	public ListNode mergeKListsV4(ListNode[] lists) {
		int k = lists.length;
		int interval = 1;
		while(interval < k){
			for(int i=0;i<k-interval;i+=2*interval){
				lists[i] = mergeTwoListsV2(lists[i],lists[i+interval]);
			}
			interval *= 2;
		}
		return lists.length>0?lists[0]:null;
	}
	public static void main(String[] args) {
		
	}

}
