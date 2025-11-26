import java.util.Scanner;

class MinHeap {
    int[] heap;
    int size;
    int capacity;

    MinHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    int parent(int i) { return (i - 1) / 2; }
    int left(int i) { return 2 * i + 1; }
    int right(int i) { return 2 * i + 2; }

    void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full!");
            return;
        }

        heap[size] = value;
        int current = size;
        size++;

        // Bubble up
        while (current != 0 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    int getMin() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return -1;
        }
        return heap[0];
    }

    int extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return -1;
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapify(0);
        return root;
    }

    void heapify(int i) {
        int smallest = i;
        int l = left(i);
        int r = right(i);

        if (l < size && heap[l] < heap[smallest])
            smallest = l;

        if (r < size && heap[r] < heap[smallest])
            smallest = r;

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    void display() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return;
        }
        System.out.print("Heap (Level Order): ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}

class PriorityQueueUsingHeap {
    MinHeap heap;

    PriorityQueueUsingHeap(int capacity) {
        heap = new MinHeap(capacity);
    }

    void enqueue(int x) {
        heap.insert(x);
    }

    int dequeue() {
        return heap.extractMin();
    }

    int peek() {
        return heap.getMin();
    }

    boolean isEmpty() {
        return heap.size == 0;
    }

    void display() {
        heap.display();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter heap capacity: ");
        int capacity = sc.nextInt();

        PriorityQueueUsingHeap pq = new PriorityQueueUsingHeap(capacity);

        while (true) {
            System.out.println("\n------ Priority Queue Using Min-Heap ------");
            System.out.println("1. Insert");
            System.out.println("2. Get Minimum");
            System.out.println("3. Remove Minimum");
            System.out.println("4. Display Heap");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int val = sc.nextInt();
                    pq.enqueue(val);
                    break;

                case 2:
                    System.out.println("Minimum Element: " + pq.peek());
                    break;

                case 3:
                    System.out.println("Removed: " + pq.dequeue());
                    break;

                case 4:
                    pq.display();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
