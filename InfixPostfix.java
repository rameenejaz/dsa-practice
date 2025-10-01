// // import java.util.Scanner;
// // public class InfixPostfix {
// //     static int precedence(char ch) {
// //         switch (ch) {
// //             case '+':
// //             return 1;
// //             case'-':
// //             return -1;
// //             case '*':
// //             return 2;
// //             case '/':
// //             return 2;
// //             case '^':
// //             return 3;
// //         }
// //         return -1;
// //     }
// //     static boolean isOperator(char ch) {
// //         return ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='^';
// //     }
// //     static String infixToPostFix(String infix) {
// //         Stack stack= new Stack(infix.length());
// //         char [] postfix=new char[infix.length()];
// //         int k=0;
// //         for (int i=0; i<infix.length(); i++) {
// //             char c=infix.charAt(i);
// //             if (c==' ') {
// //                 continue;
// //             }
// //             if (Character.isDigit(c)) {
// //                 postfix[k++]=c;
// //             }
// //             else if (c=='(') {
// //                 stack.push(c);
// //             }
// //             else if(c==')') {
// //                 while (!stack.isEmpty() && stack.peek()!='(') {
// //                     postfix[k++]=stack.pop();
// //                 }
// //                 if (!stack.isEmpty() && stack.peek()=='(') {
// //                     stack.pop();
// //                 }
// //                 else if (isOperator(c)) {
// //                     while (!stack.isEmpty() && precedence(stack.peek())>=precedence(c)) {
// //                         postfix[k++] =stack.pop();
// //                     }
// //                     stack.push(c);
// //                 }
// //             }
// //             while(!stack.isEmpty()) {
// //                 postfix[k++] =stack.pop();
// //             }
// //             char [] result =new char[k];
// //             for (int j=0; j<k; j++) {
// //                 result[j]=postfix[j];
// //             }
// //             return new String(result);
// //         }
// //         static int evaluatePostFix(String postfix) {
// //             intStack stack =new intStack(postfix.length());
// //             for (int i=0; i<postfix.length();i++) {
// //                 char c=postfix.charAt(i);
// //                 if (Character.isDigit(c)) {
// //                     stack.push(c-'0');
// //                 }
// //                 else if (isOperator(c)) {
// //                     int value2=stack.pop();
// //                     int value1=stack.pop();
// //                     int result=0;
// //                     switch (c) {
// //                         case '+':
// //                         result=value1+value2;
// //                         break;
// //                         case '-':
// //                         result=value1-value2;
// //                         break;
// //                         case '*':
// //                         result=value1*value2;
// //                         break;
// //                         case '/':
// //                         result=value1/value2;
// //                         break;
// //                         case '^':
// //                         result=1;
// //                         for (int j=0; j<value2; j++) {
// //                             result*=value1;
// //                             break;
// //                         }
// //                         stack.push(result);
// //                     }
// //                 }
// //                 return stack.pop();
// //             }
// //             public static void main(String[] args) {
// //             Scanner sc = new Scanner(System.in);
// //             System.out.print("Enter infix expression: ");
// //             String infix = sc.nextLine();

// //             String postfix =infixToPostFix(infix);
// //             System.out.println("Postfix expression: " + postfix);

// //             int result = evaluatePostFix(postfix);
// //             System.out.println("Evaluated result: " + result);
// //     }   
        
// //     }

// // }
// import java.util.Scanner;

// static String infixToPostfix(String infix) {
//         Stack stack = new Stack(infix.length());
//         char[] postfix = new char[infix.length()];
//         int k = 0;

//         for (int i = 0; i < infix.length(); i++) {
//             char c = infix.charAt(i);

//             if (c == ' ') continue;

//             if (Character.isDigit(c)) {
//                 postfix[k++] = c;
//             }
//             else if (c == '(') {
//                 stack.push(c);
//             }
//             else if (c == ')') {
//                 while (!stack.isEmpty() && stack.peek() != '(') {
//                     postfix[k++] = stack.pop();
//                 }
//                 if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
//             }
//             else if (isOperator(c)) {
//                 while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
//                     postfix[k++] = stack.pop();
//                 }
//                 stack.push(c);
//             }
//         }

//         while (!stack.isEmpty()) {
//             postfix[k++] = stack.pop();
//         }

//         // convert char array to string
//         char[] result = new char[k];
//         for (int i = 0; i < k; i++) {
//             result[i] = postfix[i];
//         }

//         return new String(result);
//     }

//     // Evaluate postfix
//     static int evaluatePostfix(String postfix) {
//         intStack stack = new intStack(postfix.length());

//         for (int i = 0; i < postfix.length(); i++) {
//             char c = postfix.charAt(i);

//             if (Character.isDigit(c)) {
//                 stack.push(c - '0');
//             } 
//             else if (isOperator(c)) {
//                 int val2 = stack.pop();
//                 int val1 = stack.pop();
//                 int result = 0;

//                 switch (c) {
//                     case '+': result = val1 + val2; break;
//                     case '-': result = val1 - val2; break;
//                     case '*': result = val1 * val2; break;
//                     case '/': result = val1 / val2; break;
//                     case '^':
//                         result = 1;
//                         for (int j = 0; j < val2; j++) result *= val1;
//                         break;
//                 }
//                 stack.push(result);
//             }
//         }
//         return stack.pop();
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.print("Enter infix expression: ");
//         String infix = sc.nextLine();

//         String postfix = infixToPostfix(infix);
//         System.out.println("Postfix expression: " + postfix);

//         int result = evaluatePostfix(postfix);
//         System.out.println("Evaluated result: " + result);
//     }

import java.util.Scanner;

// ---------------- CHAR STACK ----------------
class CharStack {
    private char[] arr;
    private int top;

    CharStack(int size) {
        arr = new char[size];
        top = -1;
    }

    void push(char c) {
        if (top < arr.length - 1) {
            arr[++top] = c;
        }
    }

    char pop() {
        if (!isEmpty()) {
            return arr[top--];
        }
        return '\0';
    }

    char peek() {
        if (!isEmpty()) {
            return arr[top];
        }
        return '\0';
    }

    boolean isEmpty() {
        return top == -1;
    }
}

// ---------------- INT STACK ----------------
class IntStack {
    private int[] arr;
    private int top;

    IntStack(int size) {
        arr = new int[size];
        top = -1;
    }

    void push(int val) {
        if (top < arr.length - 1) {
            arr[++top] = val;
        }
    }

    int pop() {
        if (!isEmpty()) {
            return arr[top--];
        }
        return 0;
    }

    boolean isEmpty() {
        return top == -1;
    }
}

// ---------------- MAIN CLASS ----------------
public class InfixPostfix {

    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    // Convert infix to postfix
    static String infixToPostfix(String infix) {
        CharStack stack = new CharStack(infix.length());
        char[] postfix = new char[infix.length()];
        int k = 0;

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                postfix[k++] = c;
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix[k++] = stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
            }
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix[k++] = stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix[k++] = stack.pop();
        }

        // convert char array to string
        char[] result = new char[k];
        for (int i = 0; i < k; i++) {
            result[i] = postfix[i];
        }

        return new String(result);
    }

    // Evaluate postfix
    static int evaluatePostfix(String postfix) {
        IntStack stack = new IntStack(postfix.length());

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } 
            else if (isOperator(c)) {
                int val2 = stack.pop();
                int val1 = stack.pop();
                int result = 0;

                switch (c) {
                    case '+': result = val1 + val2; break;
                    case '-': result = val1 - val2; break;
                    case '*': result = val1 * val2; break;
                    case '/': result = val1 / val2; break;
                    case '^':
                        result = 1;
                        for (int j = 0; j < val2; j++) result *= val1;
                        break;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine();

        String postfix = infixToPostfix(infix);
        System.out.println("Postfix expression: " + postfix);

        int result = evaluatePostfix(postfix);
        System.out.println("Evaluated result: " + result);
    }
}
