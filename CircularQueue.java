public class CircularQueue {
    int front;
    int rear;
    int size;
    int capacity;
    int arr[];
    CircularQueue(int capacity) {
        this.capacity=capacity;
        arr=new int [capacity];
        front=-1;
        rear=-1;
        size=0;

    }
    boolean isFull() {
        return size==capacity;
    }
    boolean isEmpty() {
        return size==0;
    }
    void enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        if (front==-1) {
            front=0;
        }
        rear=(rear+1)%capacity;
        arr[capacity]=data;
        size++;
        System.out.println("Enqueued: " + data);
    }
}
