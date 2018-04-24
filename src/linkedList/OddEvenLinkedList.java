package linkedList;

public class OddEvenLinkedList {
	public ListNode oddEvenList(ListNode head) {
        ListNode oddHead = new ListNode(0);
        ListNode oddNode = oddHead;
        ListNode evenHead = new ListNode(0);
         ListNode evenNode = evenHead;
        int i=1;
        while(head!=null){
            ListNode tmpNext = head.next;
            head.next = null;
            if(i%2==0){
                evenNode.next = head;
                evenNode = evenNode.next;
            }else{
                oddNode.next = head;
                oddNode = oddNode.next;
            }
            head = tmpNext;
            i++;
        }
        oddNode.next = evenHead.next;
        return oddHead.next;
    }
}
