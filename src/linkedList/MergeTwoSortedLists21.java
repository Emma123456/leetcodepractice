package linkedList;

public class MergeTwoSortedLists21 {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
            
        }
        if(l2==null){
            return l1;
        }
        ListNode mergeHead;
        if(l1.val<l2.val){
            mergeHead = l1;
            mergeHead.next = mergeTwoLists(l1.next,l2);
        }else{
            mergeHead = l2;
            mergeHead.next = mergeTwoLists(l1,l2.next);
        }
        return mergeHead;
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
		}
		if(l1!=null){
			preNode.next = l1;
		}
		if(l2!=null){
			preNode.next = l2;
		}
		return result.next;
	}
	public static void main(String[] args) {

	}

}
