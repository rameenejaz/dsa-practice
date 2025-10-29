import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    public Node(int value) {
        data = value;
        left = right = null;
    }
}

public class BST {
    Node root;

    // -------------------- INSERT --------------------
    public Node insert(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (value < root.data)
            root.left = insert(root.left, value);
        else if (value > root.data)
            root.right = insert(root.right, value);

        return root;
    }

    // -------------------- SEARCH --------------------
    public int search(Node root, int key) {
        int comparisons = 0;
        Node current = root;

        while (current != null) {
            comparisons++;
            if (key == current.data) {
                System.out.println("Element found after " + comparisons + " comparisons.");
                return comparisons;
            } else if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }

        System.out.println("Element not found after " + comparisons + " comparisons.");
        return -1;
    }

    // -------------------- DELETE --------------------
    public Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            // Case 1: No child
            if (root.left == null && root.right == null)
                return null;
            // Case 2: One child
            else if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // Case 3: Two children
            else {
                Node successor = findMin(root.right);
                root.data = successor.data;
                root.right = delete(root.right, successor.data);
            }
        }
        return root;
    }

    private Node findMin(Node root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    // -------------------- TRAVERSALS --------------------
    public void inorder(Node root) { // Left - Root - Right
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public void preorder(Node root) { // Root - Left - Right
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void postorder(Node root) { // Left - Right - Root
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // -------------------- ADDITIONAL TASK 1 --------------------
    // Count and print leaf nodes
    public int printAndCountLeafNodes(Node root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            System.out.print(root.data + " ");
            return 1;
        }

        return printAndCountLeafNodes(root.left) + printAndCountLeafNodes(root.right);
    }

    // -------------------- ADDITIONAL TASK 2 --------------------
    // Print all nodes within range [L, R]
    public void printRange(Node root, int L, int R) {
        if (root == null) return;

        if (root.data > L)
            printRange(root.left, L, R);

        if (root.data >= L && root.data <= R)
            System.out.print(root.data + " ");

        if (root.data < R)
            printRange(root.right, L, R);
    }

    // -------------------- MAIN FUNCTION --------------------
    public static void main(String[] args) {
        BST bst = new BST();
        Scanner sc = new Scanner(System.in);
        int choice, value;

        while (true) {
            System.out.println("\n\n--- BINARY SEARCH TREE MENU ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display Traversal");
            System.out.println("5. Print & Count Leaf Nodes");
            System.out.println("6. Print Nodes in Range [L, R]");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = sc.nextInt();
                    bst.root = bst.insert(bst.root, value);
                    break;

                case 2:
                    System.out.print("Enter value to search: ");
                    value = sc.nextInt();
                    bst.search(bst.root, value);
                    break;

                case 3:
                    System.out.print("Enter value to delete: ");
                    value = sc.nextInt();
                    bst.root = bst.delete(bst.root, value);
                    break;

                case 4:
                    int subChoice;
                    System.out.println("\n--- Choose Traversal Type ---");
                    System.out.println("1. Inorder (Left-Root-Right)");
                    System.out.println("2. Preorder (Root-Left-Right)");
                    System.out.println("3. Postorder (Left-Right-Root)");
                    System.out.print("Enter choice: ");
                    subChoice = sc.nextInt();

                    System.out.print("Traversal: ");
                    switch (subChoice) {
                        case 1:
                            bst.inorder(bst.root);
                            break;
                        case 2:
                            bst.preorder(bst.root);
                            break;
                        case 3:
                            bst.postorder(bst.root);
                            break;
                        default:
                            System.out.println("Invalid traversal choice!");
                    }
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Leaf Nodes: ");
                    int count = bst.printAndCountLeafNodes(bst.root);
                    System.out.println("\nTotal Leaf Nodes = " + count);
                    break;

                case 6:
                    System.out.print("Enter L: ");
                    int L = sc.nextInt();
                    System.out.print("Enter R: ");
                    int R = sc.nextInt();
                    System.out.print("Nodes in range [" + L + ", " + R + "]: ");
                    bst.printRange(bst.root, L, R);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
