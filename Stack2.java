public class Stack2 {
    char [] arr;
    int top;
    int size;
    public Stack2(int size) {
        this.size=size;
        arr=new char[size];
        top=-1;
    }
    public void push (char x) {
        if (top==size-1) {
            System.out.println("Stack overflow");
            return;
        }
        else {
            arr[++top]=x;
        }
    }
    public char pop() {
        if (top==-1) {
            System.out.println("Stack Underflow");
            return '\0'; //return null
        }
        return arr[top--];
    }
    public char peek() {
        if (top==-1) {
            return '\0';
        }
        return arr[top];
    }
    public boolean isEmpty() {
        return top==-1;
    }

}
