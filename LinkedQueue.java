public class LinkedQueue {
    private Node front;
    private Node rear;
    public LinkedQueue() {
        front=rear=null;
    }
    public boolean isEmpty() {
        return front==null;
    }
    
}
