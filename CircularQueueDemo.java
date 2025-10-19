public class CircularQueueDemo {
    public static void main(String[] args) {
        CircularQueue2 cq = new CircularQueue2(5);

        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.enqueue(40);
        cq.enqueue(50);
        cq.printQueue(); // 10 20 30 40 50

        System.out.println("Dequeued: " + cq.dequeue()); // 10
        System.out.println("Dequeued: " + cq.dequeue()); // 20
        cq.printQueue(); // 30 40 50

        cq.enqueue(60);
        cq.enqueue(70); // wraps around
        cq.printQueue(); // 30 40 50 60 70
    }
}
