import java.util.Scanner;;
public class balancedParenthesis {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your expression: ");
        String expression1=sc.nextLine();
        // String expression2="((a+b)";
        System.out.println("Expression: " + expression1 + " -> " + (isBalanced(expression1)? "Balanced": "Not Balanced"));
        // System.out.println("Expression 2: " + expression2 + "->" +isBalanced(expression2));
    
}
public static boolean isBalanced(String expression) {
        Stack2 stack=new Stack2(expression.length());
        for (int i=0; i<expression.length(); i++) {
            char ch=expression.charAt(i);
            if (ch=='(' || ch=='{' || ch=='[') {
                stack.push(ch);
            }
            else if (ch==')' || ch=='}' || ch==']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top=stack.pop();
                if (ch==')' && top!='(' || ch=='}' && top!='{' || ch==']' && top!='[') {
                return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
