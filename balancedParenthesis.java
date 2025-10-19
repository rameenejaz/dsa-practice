public class balancedParenthesis {
    public static void main(String[] args) {
        String expression1="((a+b)*c)";
        String expression2="((a+b)";
        System.out.println("Expression: " + expression1 + isBalanced(expression1));
        System.out.println("Expression 2: " + expression2 +isBalanced(expression2));
    
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
