import java.util.Scanner;
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class NestedPalindromeCheck {

    // Reverse nodes from start to end inclusive
    private static Node reverse(Node start, Node end) {
        Node prev = null;
        Node curr = start;
        Node stop = end.next; // stop after end

        while (curr != stop) {
            Node nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        return prev; // new head of reversed section
    }

    // Check if one segment is palindrome and restore it
    private static boolean isPalindromeSegment(Node start, int m) {
        if (m == 1) return true;

        // Find middle of segment
        Node mid = start;
        for (int i = 0; i < (m / 2) - 1; i++) {
            mid = mid.next;
        }

        // Find end of segment
        Node end = start;
        for (int i = 0; i < m - 1; i++) {
            end = end.next;
        }

        // Determine starting node of second half
        Node secondHalfStart;
        if (m % 2 == 0) {
            secondHalfStart = mid.next;
        } else {
            secondHalfStart = mid.next.next;
        }

        // Save pointer before second half
        Node beforeSecondHalf = (m % 2 == 0) ? mid : mid.next;

        // Reverse the second half
        Node revHead = reverse(secondHalfStart, end);

        // Compare halves
        Node p1 = start;
        Node p2 = revHead;
        boolean isPal = true;
        for (int i = 0; i < m / 2; i++) {
            if (p1.data != p2.data) {
                isPal = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore second half (reverse again)
        Node restoredHead = reverse(revHead, end);

        // Reconnect restored half back
        beforeSecondHalf.next = restoredHead;

        return isPal;
    }

    // Check all segments
    public static boolean checkAllSegments(Node head, int m) {
        Node curr = head;
        while (curr != null) {
            if (!isPalindromeSegment(curr, m)) {
                return false;
            }
            // move curr to next segment
            for (int i = 0; i < m; i++) {
                curr = curr.next;
            }
        }
        return true;
    }

    // Append helper
    public static Node append(Node head, int val) {
        if (head == null) return new Node(val);
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = new Node(val);
        return head;
    }

    // Print helper
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    // Main with user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take linked list input
        System.out.print("Enter number of nodes in the list: ");
        int n = sc.nextInt();

        Node head = null;
        System.out.println("Enter the values:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            head = append(head, val);
        }

        // Take number of segments
        System.out.print("Enter the number of segments to divide the list into: ");
        int segments = sc.nextInt();

        if (n % segments != 0) {
            System.out.println("Error: List length is not divisible by number of segments.");
            return;
        }

        int m = n / segments; // size of each segment

        // Check palindromes
        boolean result = checkAllSegments(head, m);

        System.out.println("Are all segments palindrome? " + result);

        // Show restored list
        System.out.print("Restored Linked List: ");
        printList(head);
    }
}
