package linkedList;

public class InsertionSortList147 {
	public ListNode insertionSortList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode curr = head.next;
        while(curr!=null){
            ListNode tmp = curr.next;
            ListNode node = head;
            ListNode preNode = null;//插入位置
            while(node!=curr && node.val<=curr.val){
                preNode = node;
                node = node.next;
            }
            if(preNode==null){
                curr.next = node;
                head = curr;
            }else{
                preNode.next = curr;
                curr.next = node;
            }
            while(node.next!=curr){
            	node = node.next;
            }
            node.next = tmp;
            curr = tmp;
        }
        return head;
    }
	
	/**
	 * 建一个fake节点作为排序好的链表
	 * @param head
	 * @return
	 */
	public ListNode insertionSortListV2(ListNode head) {
		ListNode curr = head,next = null;
		ListNode sortedHead = new ListNode(-1);
		while(curr!=null){
			next = curr.next;
			
			ListNode p = sortedHead;
			while(p.next!=null && p.next.val<curr.val){
				p = p.next;
			}
			curr.next = p.next;//已经排序好的大于curr.val的部分
			p.next = curr;
			
			curr = next;
		}
		return sortedHead.next;
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
		node1 = new InsertionSortList147().insertionSortListV2(node1);
		while(node1!=null){
			System.out.print(node1.val+"\t");
			node1 = node1.next;
		}
	}

}
