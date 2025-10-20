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
    
}
