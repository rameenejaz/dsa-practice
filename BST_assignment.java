class Node {
    int data;
    Node left, right;
    Node(int d){ data = d; }
}

class BST_assignment {

    // ------------------ MIN SWAPS FOR INORDER ------------------
    int minSwapsToSort(int[] arr){
        int n = arr.length;
        int[][] pair = new int[n][2];
        for(int i=0;i<n;i++){
            pair[i][0] = arr[i];
            pair[i][1] = i;
        }

        // simple bubble sort on pair
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(pair[i][0] > pair[j][0]){
                    int[] temp = pair[i];
                    pair[i] = pair[j];
                    pair[j] = temp;
                }
            }
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for(int i=0;i<n;i++){
            if(visited[i] || pair[i][1]==i) continue;

            int cycle=0;
            int j=i;
            while(!visited[j]){
                visited[j]=true;
                j=pair[j][1];
                cycle++;
            }
            if(cycle>1) swaps += (cycle-1);
        }
        return swaps;
    }

    // ------------------ MIN SWAPS WITH ROOT FIXED ------------------
    int minSwapsToSortWithRoot(int[] preorder, int[] inorder){
        int n = inorder.length;
        int rootVal = preorder[0];
        int rootIndex = -1;
        for(int i=0;i<n;i++){
            if(inorder[i]==rootVal){
                rootIndex=i;
                break;
            }
        }
        if(rootIndex==-1) rootIndex=n/2;

        int swaps=0;

        // left subtree
        if(rootIndex>0){
            int[] left = new int[rootIndex];
            for(int i=0;i<rootIndex;i++) left[i]=inorder[i];
            swaps += minSwapsToSort(left);
        }

        // right subtree
        if(rootIndex<n-1){
            int[] right = new int[n-rootIndex-1];
            for(int i=rootIndex+1;i<n;i++) right[i-rootIndex-1]=inorder[i];
            swaps += minSwapsToSort(right);
        }

        return swaps;
    }

    // ------------------ CORRECT INORDER WITH ROOT FIXED ------------------
    int[] correctInorderWithRoot(int[] preorder, int[] inorder){
        int n = inorder.length;
        int rootVal = preorder[0];
        int rootIndex = -1;
        for(int i=0;i<n;i++){
            if(inorder[i]==rootVal){
                rootIndex=i;
                break;
            }
        }
        if(rootIndex==-1) rootIndex=n/2;

        // left
        int leftSize=rootIndex;
        int[] left=new int[leftSize];
        for(int i=0;i<leftSize;i++) left[i]=inorder[i];
        bubbleSort(left);

        // right
        int rightSize=n-rootIndex-1;
        int[] right=new int[rightSize];
        for(int i=0;i<rightSize;i++) right[i]=inorder[rootIndex+1+i];
        bubbleSort(right);

        // rebuild
        int[] corrected=new int[n];
        for(int i=0;i<leftSize;i++) corrected[i]=left[i];
        corrected[rootIndex]=rootVal;
        for(int i=0;i<rightSize;i++) corrected[rootIndex+1+i]=right[i];

        return corrected;
    }

    void bubbleSort(int[] arr){
        int n=arr.length;
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    // ------------------ BUILD BST ------------------
    Node buildBSTFromSortedArray(int[] arr, int start, int end){
        if(start>end) return null;
        int mid=(start+end)/2;
        Node root=new Node(arr[mid]);
        root.left=buildBSTFromSortedArray(arr,start,mid-1);
        root.right=buildBSTFromSortedArray(arr,mid+1,end);
        return root;
    }

    // ------------------ HEIGHT ------------------
    int height(Node root){
        if(root==null) return 0;
        int l=height(root.left);
        int r=height(root.right);
        return 1+ (l>r?l:r);
    }

    // ------------------ INORDER PRINT ------------------
    void inorderPrint(Node root){
        if(root==null) return;
        inorderPrint(root.left);
        System.out.print(root.data+" ");
        inorderPrint(root.right);
    }

    // ------------------ PRETTY PRINT TREE ------------------
    void display(Node root){
        printTree(root,0);
    }

    void printTree(Node root, int level){
        if(root==null) return;
        printTree(root.right, level+1);
        for(int i=0;i<level;i++) System.out.print("    ");
        System.out.println(root.data);
        printTree(root.left, level+1);
    }
}
