public class Stack2 {
    int [] arr;
    int top;
    int size;
    public Stack2(int size) {
        arr=new int[size];
        top=-1;
    }
    public void push (int x) {
        if (top==-1) {
            System.out.println("Stack overflow");
        }
        else {
            top++;
            arr[top]=x;
        }
    }
    public void pop() {
        if (top==-1) {
            System.out.println("Stack underflow");
        }
        else {
            System.out.println(arr[top--]);
        }
    }

}
