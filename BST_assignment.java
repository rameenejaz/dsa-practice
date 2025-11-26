import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int d){ data = d; }
}

class BST_assignment {

    
    int minSwapsToSort(int[] arr){
        int n = arr.length;
        int[][] pair = new int[n][2];
        for(int i=0;i<n;i++){
            pair[i][0] = arr[i];
            pair[i][1] = i;
        }
        Arrays.sort(pair, (a,b)-> a[0]-b[0]);

        boolean[] visited = new boolean[n];
        int swaps = 0;
        for(int i=0;i<n;i++){
            if(visited[i] || pair[i][1]==i) continue;
            int cycle=0, j=i;
            while(!visited[j]){
                visited[j]=true;
                j=pair[j][1];
                cycle++;
            }
            if(cycle>1) swaps += (cycle-1);
        }
        return swaps;
    }
    int[] correctInorder(int[] inorder){
        int[] copy = Arrays.copyOf(inorder, inorder.length);
        Arrays.sort(copy);
        return copy;
    }

    Node buildBSTFromSortedArray(int[] arr, int start, int end){
        if(start > end) return null;
        int mid = (start+end)/2;
        Node root = new Node(arr[mid]);
        root.left = buildBSTFromSortedArray(arr, start, mid-1);
        root.right = buildBSTFromSortedArray(arr, mid+1, end);
        return root;
    }
    int height(Node root){
        if(root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }
    void inorderPrint(Node root){
        if(root==null) return;
        inorderPrint(root.left);
        System.out.print(root.data + " ");
        inorderPrint(root.right);
    }
    // void display(Node root){
    //     if(root==null) { System.out.println("(empty)"); return; }
    //     Queue<Node> q = new LinkedList<>();
    //     q.add(root);
    //     while(!q.isEmpty()){
    //         int size = q.size();
    //         for(int i=0;i<size;i++){
    //             Node cur = q.poll();
    //             System.out.print(cur.data + " ");
    //             if(cur.left != null) q.add(cur.left);
    //             if(cur.right != null) q.add(cur.right);
    //         }
    //         System.out.println();
    //     }
    // }
    void display(Node root) {
    printTree(root, 0);
}

void printTree(Node root, int level) {
    if (root == null) return;

    // print right subtree first
    printTree(root.right, level + 1);

    // indent based on level
    for (int i = 0; i < level; i++)
        System.out.print("    ");

    // print current node
    System.out.println(root.data);

    // print left subtree
    printTree(root.left, level + 1);
}
}
