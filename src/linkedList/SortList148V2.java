package linkedList;

public class SortList148V2 {
	public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        
        ListNode left, right, tail, dummy;
        for (int step = 1; step < length; step <<= 1) {
            // NOTE: no need to assign head to dummy.next
            // dummy node need to be created within for-loop
            // because the head may change within each merge operation
            dummy = new ListNode(0);
            cur = head;
            tail = dummy;
            while (cur != null) {
                left = cur;
                right = split(left, step);
                cur = split(right, step);
                tail = merge(left, right, tail);
            }
            head = dummy.next;// update head
        }
        
        return head;
    }
    
    // divide the list into two linked list.
    // the first list contains n nodes (or all nodes).
    // return the head of the second list (or null) 
    private ListNode split(ListNode head, int n) {
        for (int i = 1; head != null && i < n; i++) {
            head = head.next;
        }
        if (head == null) return null;
        ListNode second = head.next;
        head.next = null;
        return second;
    }
    
    // merge the two sorted linked list l1 and l2
    // append the result to the list node head
    // return the tail of the merged sorted list
    private ListNode merge(ListNode l1, ListNode l2, ListNode head) {
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
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
		ListNode head = new SortList148V2().sortList(node1);
		while(head!=null){
			System.out.print(head.val+"\t");
			head = head.next;
		}
	}
}
