public class CircularQueue2 {
    private int [] arr;
    private int front, rear,size, capacity;
    public CircularQueue2(int capacity) {
        this.capacity=capacity;
        arr=new int[capacity];
        front=0;
        rear=-1;
        size=0;
    }
    public boolean isEmpty() {
        return size==0;
    }
    public boolean isFull() {
        return size==capacity;
    }
    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue Overflow");
            return;
        }
        rear=(rear+1)%capacity; //to wrap around
        arr[rear]=x;
        size++;
    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue underflow");
            return -1;
        }
        int value=arr[front];
        front=(front+1)%capacity;
        size--;
        return value;
    }
    public int peek() {
        if (isEmpty())
        return -1;
        return arr[front];
    }
    public void printQueue() {
        if(isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        int i=front;
        for (int count=0; count<size;count++) {
            System.out.print(arr[i] + " ");
            i=(i+1)%capacity;
        }
        System.out.println();
    }
}
