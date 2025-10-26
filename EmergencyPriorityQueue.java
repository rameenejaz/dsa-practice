class Incident {
    int incidentID;
    int severity;
    double distance;
    int timeSinceReported;
    double priority;

    // Constructor
    public Incident(int incidentID, int severity, double distance, int timeSinceReported) {
        this.incidentID = incidentID;
        this.severity = severity;
        this.distance = distance;
        this.timeSinceReported = timeSinceReported;
        computePriority();
    }

    // Priority formula
    public void computePriority() {
        int w1 = 7, w2 = 5, w3 = 3;
        // w1: Severity most important
        // w2: Moderate importance (closer = higher priority)
        // w3: Less important but still increases urgency over time
        priority = w1 * severity + w2 * (1 / distance) + w3 * timeSinceReported;
    }

    @Override
    public String toString() {
        return "Incident " + incidentID + " | Priority=" + String.format("%.2f", priority);
    }
}

public class EmergencyPriorityQueue {
    private Incident[] heap;
    private int size;
    private final int capacity;

    // Constructor
    public EmergencyPriorityQueue(int capacity) {
        this.capacity = capacity;
        heap = new Incident[capacity];
        size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        Incident temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Heapify up: used after insert
    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)].priority < heap[i].priority) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Heapify down: used after extract
    private void heapifyDown(int i) {
        int largest = i;
        int l = left(i);
        int r = right(i);

        if (l < size && heap[l].priority > heap[largest].priority)
            largest = l;
        if (r < size && heap[r].priority > heap[largest].priority)
            largest = r;

        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    // 1. Insert new incident
    public void insertIncident(int id, int severity, double distance, int timeReported) {
        if (size == capacity) {
            System.out.println("Queue full! Cannot insert new incident.");
            return;
        }
        Incident inc = new Incident(id, severity, distance, timeReported);
        heap[size] = inc;
        size++;
        heapifyUp(size - 1);
    }

    // 2. Extract highest-priority incident
    public Incident extractNextIncident() {
        if (size == 0) {
            System.out.println("No incidents in queue!");
            return null;
        }
        Incident root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return root;
    }

    // 3. Update an existing incident
    public void updateIncident(int id, int newSeverity, double newDistance, int newTime) {
        for (int i = 0; i < size; i++) {
            if (heap[i].incidentID == id) {
                heap[i].severity = newSeverity;
                heap[i].distance = newDistance;
                heap[i].timeSinceReported = newTime;
                heap[i].computePriority();
                heapifyUp(i);
                heapifyDown(i);
                System.out.println("Incident " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Incident " + id + " not found.");
    }

    // 4. Display queue (sorted order)
    public void displayQueue() {
        if (size == 0) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Current Queue (Highest → Lowest Priority):");

        // Copy heap elements into temp array for sorting display only
        Incident[] temp = new Incident[size];
        for (int i = 0; i < size; i++) temp[i] = heap[i];

        // Simple bubble sort for display
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (temp[j].priority < temp[j + 1].priority) {
                    Incident t = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = t;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.println(temp[i]);
        }
    }

    // 5. Simulate time passage
    public void simulateTimePassage(int minutes) {
        if (size == 0) {
            System.out.println("Queue is empty. Nothing to update.");
            return;
        }

        System.out.println("Simulating " + minutes + " minutes passed...");
        for (int i = 0; i < size; i++) {
            heap[i].timeSinceReported += minutes;
            heap[i].computePriority();
        }

        // Rebuild heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }

        System.out.println("Incident priorities updated.");
    }

    // ---------- MAIN TEST ----------
    public static void main(String[] args) {
        EmergencyPriorityQueue pq = new EmergencyPriorityQueue(100);

        pq.insertIncident(101, 8, 2.0, 10);
        pq.insertIncident(102, 5, 1.0, 5);
        pq.insertIncident(103, 9, 5.0, 3);
        pq.displayQueue();

        System.out.println("\nExtracted Incident: " + pq.extractNextIncident());
        pq.simulateTimePassage(10);
        pq.displayQueue();

        pq.updateIncident(103, 10, 2.0, 30);
        pq.displayQueue();
    }
}
