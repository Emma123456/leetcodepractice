package linkedList;

public class SortList148V2 {
	/**
	 * 列表中的 第0个、第1个合并排序、第2个、第3个合并排序....
	 * 之后第0,1 和 第2，3 两个merge
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        int length = 0;
        ListNode node = head;
        while(node!=null){
        	length++;
        	node = node.next;
        }
        ListNode dummy = new ListNode(-1);
        for(int step = 1;step<length; step<<=1){
        	ListNode tail = dummy;//每一次处理的队列的队尾
            ListNode curr = head;
        	while(curr!=null){
        		ListNode left = curr;
            	ListNode right = null;
            	right = split(left,step);//从left开始，数step个节点作为第二个列表的头节点
            	curr = split(right,step);//从right开始，数step个节点，返回的节点用于下次合并
            	tail = merge(left,right,tail);
        	}
        	head = dummy.next;
        }
       return dummy.next;
    }
    
   
    
	private ListNode split(ListNode head, int step) {
		for (int i = 1; head != null && i < step; i++) {// 少走一步
			head = head.next;
		}
		if (head == null)
			return null;
		ListNode tmp = head.next;
		head.next = null;
		return tmp;
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
		ListNode node3 = new ListNode(-2);
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
