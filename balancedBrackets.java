import java.util.Scanner;

public class balancedBrackets {

    static class CharStack {
        char[] arr;
        int top;

        CharStack(int size) {
            arr = new char[size];
            top = -1;
        }

        void push(char c) {
            arr[++top] = c;
        }

        char pop() {
            return arr[top--];
        }

        char peek() {
            return arr[top];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    static boolean isBalanced(String expr) {
        CharStack stack = new CharStack(expr.length());

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // push opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }

            // closing brackets
            else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) 
                return false; // nothing to match

                char top = stack.pop();

                // check matching
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        // balanced if nothing remains
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter expression with brackets: ");
        String expr = sc.nextLine();

        if (isBalanced(expr))
            System.out.println("Balanced");
        else
            System.out.println("Not Balanced");
    }
}