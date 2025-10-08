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
    int dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }
        int removed=arr[front];
        front=(front+1)%capacity;
        size--;
        if (size==0) {
            front=-1;
            rear=-1;
        }
        System.out.println("Dequeued: " + removed);
        return removed;
    }
    void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue:");
        for (int i=0; i<size; i++) {
            System.out.print(arr[(front+1)%capacity]+ " ");
        }
         System.out.println();
    }
}
