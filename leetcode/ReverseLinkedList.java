package leetcode;

import java.util.List;
import java.util.Stack;

public class ReverseLinkedList {
    public static void main(String[] args) {
        final ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Using 3 pointers: ");
        printList(head);
        ListNode reversedHead = reverseList(head);
        printList(reversedHead);

        System.out.println("Using recursion: ");
        printList(reversedHead);
        reversedHead = reverseList(reversedHead);
        printList(reversedHead);
    }

    // Using 3 pointers Time O(n) Space O(1)
    static ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null, next;

        while (curr != null) {
            next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    // Using recursion Time O(n) Space O(n)
    static ListNode reverseListRecur(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rest = reverseListRecur(head.next);

        head.next.next = head;
        head.next = null;
        return rest;
    }

    static void printListRecur(ListNode head) {
        if (head == null) {
            System.out.println();
            return;
        }

        System.out.print(head.val + " ");
        printListRecur(head.next);
    }

    static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
