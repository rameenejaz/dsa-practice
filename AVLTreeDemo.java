import java.util.Scanner;

public class AVLTreeDemo {

    // AVL Tree implementation
    static class AVLTree {

        // Node definition
        static class Node {
            int key;
            int height;
            Node left, right;

            Node(int key) {
                this.key = key;
                this.height = 1; // height of a new node is 1
            }
        }

        Node root;
        String lastRotation = "No rotation";

        // Utility: height of a node
        int height(Node n) {
            return (n == null) ? 0 : n.height;
        }

        // Utility: balance factor of a node
        int getBalance(Node n) {
            if (n == null) return 0;
            return height(n.left) - height(n.right);
        }

        // Right rotation (used in LL and part of RL)
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            // Perform rotation
            x.right = y;
            y.left = T2;

            // Update heights
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            // Return new root
            return x;
        }

        // Left rotation (used in RR and part of LR)
        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            // Perform rotation
            y.left = x;
            x.right = T2;

            // Update heights
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            // Return new root
            return y;
        }

        // Specific rotation wrappers to set lastRotation
        Node rotateLL(Node z) {
            lastRotation = "LL";
            return rightRotate(z);
        }

        Node rotateRR(Node z) {
            lastRotation = "RR";
            return leftRotate(z);
        }

        Node rotateLR(Node z) {
            lastRotation = "LR";
            z.left = leftRotate(z.left);
            return rightRotate(z);
        }

        Node rotateRL(Node z) {
            lastRotation = "RL";
            z.right = rightRotate(z.right);
            return leftRotate(z);
        }

        // Insert a key into the AVL tree
        Node insert(Node node, int key) {
            // 1. Normal BST insert
            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                // Duplicate keys not allowed, do nothing
                return node;
            }

            // 2. Update height
            node.height = Math.max(height(node.left), height(node.right)) + 1;

            // 3. Get balance factor
            int balance = getBalance(node);

            // 4. Rebalance if needed

            // Left heavy
            if (balance > 1) {
                if (key < node.left.key) {
                    // LL case
                    return rotateLL(node);
                } else if (key > node.left.key) {
                    // LR case
                    return rotateLR(node);
                }
            }

            // Right heavy
            if (balance < -1) {
                if (key > node.right.key) {
                    // RR case
                    return rotateRR(node);
                } else if (key < node.right.key) {
                    // RL case
                    return rotateRL(node);
                }
            }

            // No rotation needed
            return node;
        }

        // Utility: node with minimum key value in a subtree
        Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        // Delete a key from the AVL tree
        Node delete(Node node, int key) {
            // 1. Normal BST delete
            if (node == null) {
                return null;
            }

            if (key < node.key) {
                node.left = delete(node.left, key);
            } else if (key > node.key) {
                node.right = delete(node.right, key);
            } else {
                // node to be deleted found
                if (node.left == null || node.right == null) {
                    Node temp = (node.left != null) ? node.left : node.right;

                    // No child case
                    if (temp == null) {
                        node = null;
                    } else {
                        // One child case
                        node = temp;
                    }
                } else {
                    // Node with two children:
                    // Get inorder successor (smallest in right subtree)
                    Node temp = minValueNode(node.right);

                    // Copy the inorder successor's data to this node
                    node.key = temp.key;

                    // Delete the inorder successor
                    node.right = delete(node.right, temp.key);
                }
            }

            // If the tree had only one node
            if (node == null) {
                return null;
            }

            // 2. Update height
            node.height = Math.max(height(node.left), height(node.right)) + 1;

            // 3. Get balance factor
            int balance = getBalance(node);

            // 4. Rebalance if needed

            // Left heavy
            if (balance > 1) {
                if (getBalance(node.left) >= 0) {
                    // LL case
                    return rotateLL(node);
                } else {
                    // LR case
                    return rotateLR(node);
                }
            }

            // Right heavy
            if (balance < -1) {
                if (getBalance(node.right) <= 0) {
                    // RR case
                    return rotateRR(node);
                } else {
                    // RL case
                    return rotateRL(node);
                }
            }

            return node;
        }

        // Pre-order traversal
        void preOrder(Node node) {
            if (node != null) {
                System.out.print(node.key + " ");
                preOrder(node.left);
                preOrder(node.right);
            }
        }

        // In-order traversal
        void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.key + " ");
                inOrder(node.right);
            }
        }

        // Height of the entire tree (height of root)
        int getTreeHeight() {
            return height(root);
        }
    }

    // MAIN: take input from user
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== AVL Tree Menu =====");
            System.out.println("1. Insert node");
            System.out.println("2. Delete node");
            System.out.println("3. In-order traversal");
            System.out.println("4. Pre-order traversal");
            System.out.println("5. Height of tree");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Exiting.");
                break;
            }

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter key to insert: ");
                    int key = sc.nextInt();
                    tree.lastRotation = "No rotation";  // reset
                    tree.root = tree.insert(tree.root, key);

                    System.out.print("Pre-order after insertion: ");
                    tree.preOrder(tree.root);
                    System.out.println();

                    System.out.println("Height of root: " + tree.getTreeHeight());
                    System.out.println("Rotation performed: " + tree.lastRotation);
                    break;
                }
                case 2: {
                    System.out.print("Enter key to delete: ");
                    int key = sc.nextInt();
                    tree.lastRotation = "No rotation";  // reset
                    tree.root = tree.delete(tree.root, key);

                    System.out.print("Pre-order after deletion: ");
                    tree.preOrder(tree.root);
                    System.out.println();

                    System.out.println("Height of root: " + tree.getTreeHeight());
                    System.out.println("Rotation performed: " + tree.lastRotation);
                    break;
                }
                case 3: {
                    System.out.print("In-order traversal: ");
                    tree.inOrder(tree.root);
                    System.out.println();
                    break;
                }
                case 4: {
                    System.out.print("Pre-order traversal: ");
                    tree.preOrder(tree.root);
                    System.out.println();
                    break;
                }
                case 5: {
                    System.out.println("Height of tree: " + tree.getTreeHeight());
                    break;
                }
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
