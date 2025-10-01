public class Stack {
private char [] array;
private int top;

Stack (int size) {
    array=new char[size];
    top=-1;
}
void push (char c) {
    if (top<array.length-1) {
        array[++top]=c;
    }
}
char pop () {
    if (!isEmpty()) {
        return array[top--];
    }
    return '\0';
}
char peek() {
    if (!isEmpty()) {
        return array[top];
    }
    return '\0';
}
boolean isEmpty() {
    return top==-1;
}
}