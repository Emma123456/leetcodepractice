package linkedList;

public class SortList148 {
	/**
	 * 分治法，递归版
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
		if(head==null || head.next ==null){
			return head;
		}
		ListNode fast = head,slow = head,preSlow = null;
		while(fast!=null && fast.next!=null){
			fast=fast.next.next;
			preSlow = slow;
			slow = slow.next;
		}
		if(fast!=null){
			preSlow = slow;
			slow = slow.next;
		}
		preSlow.next = null;
		ListNode head1 = sortList(head);
		ListNode head2 = sortList(slow);
		System.out.println(head1.val+" "+head2.val);
		return merge(head1,head2);
    }
	
	public ListNode merge(ListNode l1, ListNode l2) {
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
	
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(-1);
		ListNode node2 = new ListNode(5);
		ListNode node3 = new ListNode(-1);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(0);
		ListNode node6 = new ListNode(6);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		ListNode head = new SortList148().sortList(node1);
		while(head!=null){
			System.out.print(head.val+"\t");
			head = head.next;
		}
	}
}
