package others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoutingNodes {
    public static void main(String[] args) {
        // Node root = Node.create(0);
        // root.right = Node.create(1);
        // root.right.right = Node.create(2);    
        // root.right.right.right = Node.create(3);
        // root.right.right.right.right = Node.create(4);

        Node root = Node.create(2);
        root.left = Node.create(1);
        root.right = Node.create(3);
        root.left.left = Node.create(4);
        root.left.right = Node.create(5);
        root.right.right = Node.create(6);

        System.out.println(countTotalNodes(root));
        totalSubTreeNodes(root);
    }

    static int countTotalNodes(Node root) {
        // Base case
        if (root == null) {
            return 0;
        }

        int l = countTotalNodes(root.left);
        int r = countTotalNodes(root.right);

        return l + r + 1;
    }

    static List<Integer> totalSubTreeNodes(Node root) {
        int n = countTotalNodes(root);
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        countSubTreeNodes(root, result);

        for (int i : result) System.out.print(i + " ");

        return result;
    }

    static int countSubTreeNodes(Node node, List<Integer> result) {
        // Base case
        if (node == null) {
            return 0;
        }

        int leftCount = countSubTreeNodes(node.left, result);
        int rightCount = countSubTreeNodes(node.right, result);

        int totatlCount = 1 + leftCount + rightCount;
        result.set(node.data - 1, totatlCount);
        return totatlCount;
    }

    static class Node {
        public int data;
        public Node left, right;

        Node() {
            data = 0;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node[data=" + data + ", left=" + left + ", right=" + right + "]";
        }

        static Node create(int data) {
            final Node node = new Node();
            node.data = data;
            node.left = node.right = null;
            return node;
        }
    }
}
