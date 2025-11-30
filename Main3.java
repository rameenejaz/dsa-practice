import java.util.*;

public class Main3 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n=sc.nextInt();

        int[] preorder=new int[n];
        int[] inorder=new int[n];

        System.out.println("Enter preorder traversal:");
        for(int i=0;i<n;i++) preorder[i]=sc.nextInt();

        System.out.println("Enter inorder traversal (may be corrupted):");
        for(int i=0;i<n;i++) inorder[i]=sc.nextInt();

        System.out.print("Enter k (max allowed swaps): ");
        int k=sc.nextInt();

        BST_assignment obj=new BST_assignment();

        int swaps=obj.minSwapsToSortWithRoot(preorder,inorder);
        if(swaps>k){
            System.out.println("\nCannot be corrected — requires "+swaps+" swaps but k = "+k);
            sc.close();
            return;
        }

        System.out.println("\nSwaps used: "+swaps);

        int[] correctedInorder=obj.correctInorderWithRoot(preorder,inorder);
        Node root=obj.buildBSTFromSortedArray(correctedInorder,0,n-1);

        System.out.print("\nCorrected inorder: ");
        obj.inorderPrint(root);
        System.out.println();

        System.out.println("Height of BST: "+obj.height(root));

        System.out.println("\nCorrected BST:");
        obj.display(root);

        sc.close();
    }
}
