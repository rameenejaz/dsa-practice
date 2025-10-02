public class PositionStack {
    Position[] stack;
    int top;
    PositionStack(int size) {
        stack=new Position[size];
        top=-1;
    }
    void push (Position p) {
        stack[++top]=p;
    }
    Position pop() {
        return stack[top--];
    }
    Position peek() {
        return stack[top];
    }
    boolean isEmpty() {
        return top==-1;
    }
    void printPath() {
        for (int i=0; i<=top; i++) {
            System.out.println(stack[i] + " ");
        }
        System.out.println();
    }
}
