package linkedList;

public class PartitionList86 {
	public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(-1);
        ListNode biggerHead = new ListNode(-1);
        ListNode smallNode = smallHead;
        ListNode biggerNode = biggerHead;
        while(head!=null){
            if(head.val<x){
                smallNode.next = head;
                smallNode = smallNode.next;
            }else{
                biggerNode.next = head;
                biggerNode = biggerNode.next;
            }
            ListNode tmp = head.next;
            head.next = null;
            head = tmp;
        }
        smallNode.next = biggerHead.next;
        return smallHead.next;
    }
}
