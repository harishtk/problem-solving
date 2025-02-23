package leetcode;

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode D = new ListNode();
        ListNode curr = D;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;


            int sum = n1 + n2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            System.out.println(String.format("n1=%d, n2=%d, sum=%d digit=%d carry=%d", n1, n2, sum, digit,carry));

            ListNode newNode = new ListNode(digit);
            curr.next = newNode;
            curr = newNode;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        return D.next;
    }

    int walkNodes(ListNode node) {
        if (node == null) {
            return 0;
        }
        return Integer.parseInt(walkNodes(node.next) + "" + node.val);
    }

    void printNodes(ListNode n) {
        if (n == null) {
            System.out.println();
            return;
        }
        System.out.print(n.val + "");
        printNodes(n.next);
    }
}
