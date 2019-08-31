package homework.linkedlist;

public class LinkedListCycle141 {
    /**
     * 快慢指针
     * 还可以用hash
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head,slow = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow) return true;
        }
        return false;
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
