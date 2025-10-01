public class intStack {
    private int [] array;
    private int top;

    intStack(int size) {
        array=new int[size];
        top=-1;
    }
    void push (int value) {
        if (top<array.length-1) {
            array[++top]=value;
        }
    }
    int pop () {
        if (!isEmpty()) {
            return array[top--];
        }
        return 0;
    }
    boolean isEmpty() {
        return top==-1;
    }
    
}
