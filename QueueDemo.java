public class QueueDemo {
    public static void main(String[] args) {
        MyQueue q=new MyQueue(5);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.printQueue();
        System.out.println("Dequeued: " + q.dequeue());
        q.printQueue();
        System.out.println("Front: " + q.peek());
    }
}
