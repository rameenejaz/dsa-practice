public class LinkedQueue {
    private Node front;
    private Node rear;
    public LinkedQueue() {
        front=rear=null;
    }
    public boolean isEmpty() {
        return front==null;
    }
    public void enqueue(int data) {
        Node newNode=new Node(data);
        if (rear==null) { //empty queue
            front=rear=newNode;
            return;
        }
        rear.next=newNode;
        rear=newNode;
    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue underflow");
            return -1;
        }
        int value=front.data;
        front=front.next;
        if(front==null) { //if queue becomes empty
            rear=null;
        }
        return value;
    }
    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return front.data;
    }
}
