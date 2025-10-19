public class MyQueue {
    private int [] arr;
    private int front, rear, size;
    public MyQueue(int capacity) {
        arr=new int[capacity];
        front=0;
        rear=-1;
        size=0;
    }
    public void enqueue(int x) {
        if (rear==arr.length-1) {
            System.out.println("Queue Overflow");
            return;
        }
        else {
            rear++;
            arr[rear]=x;
            size++;
        }
    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return-1;
        }
        int value=arr[front];
        front++;
        size--;
        return value;
    }
    public int peek() {
        if (isEmpty()){
            return -1;
        }
        return arr[front];
    }
    public boolean isEmpty() {
        return size==0;
    }
    public void printQueue() {
        for (int i=front; i<rear; i++) {
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }
}
