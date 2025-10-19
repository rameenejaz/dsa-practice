import java.util.Scanner;
public class infixToPostfix {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String expression= sc.next();
        System.out.println("infix:" + expression);
        System.out.println("PostFix: " +infixToPostfix(expression));

    }
    public static int precedence(char op) {
        if (op=='+' || op=='-')
        return 1;
        if (op=='*' || op=='/')
        return 2;
        if (op=='^')
        return 3;
        return -1;
    }
    public static String infixToPostfix(String expression) {
        String result="";
        Stack2 stack=new Stack2(expression.length());
        for (int i=0; i<expression.length();i++) {
            char ch=expression.charAt(i);
            //for operand push to output directly
            if (Character.isLetterOrDigit(ch)) {
                result+=ch;
            }
            // for opening parenthesis push
            else if(ch=='(') {
                stack.push(ch);
            }
            // for closing parenthesis pop
            else if (ch==')') {
                while(!stack.isEmpty() && stack.peek()!='(') {
                    result+=stack.pop();
                }
                stack.pop();
            }
            //if it is an operator pop it
            else {
                while(!stack.isEmpty() && precedence(stack.peek())>=precedence(ch)) {
                    result+=stack.pop();
                }
                stack.push(ch);;
            }
        }
        while (!stack.isEmpty()) {
        result+=stack.pop();
    }
    return result;
    }
    
}
