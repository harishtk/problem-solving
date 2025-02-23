package leetcode;


public class LinkedListMiddleNode {
    public static void main(String[] args) {
        final ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(middleNode(head).val);
        System.out.println(middleNodeFast(head).val);
    }

    // Naive approach
    public static ListNode middleNode(ListNode head) {
        int n = getLength(head);

        int mid = n / 2;
        while (mid > 0) {
            head = head.next;
            mid--;
        }

        return head;
    }

    // Using Tortoise and Hare 
    public static ListNode middleNodeFast(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    static int getLength(ListNode head) {
        ListNode curr = head;
        int i = 0;
        while (curr != null) {
            i++;
            curr = curr.next;
        }
        return i;
    }
}
