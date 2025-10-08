public class EmergencyRoomQueue {
    Patient [] queue;
    int size;
    int orderCount;
    EmergencyRoomQueue(int capacity) {
        queue=new Patient[capacity];
        size=0;
        orderCount=0;
    }
    void addPatient(String id, int severity) {
        if (size == queue.length) {
            System.out.println("Queue is full!");
            return;
        }
        queue[size++] = new Patient(id, severity, orderCount++);
    }
    void treatNextPatient() {
        if (size==0) {
            System.out.println("No patients to treat.");
            return;
        }
        int highestIndex = 0;
        for (int i=0; i<size; i++) {
            if (queue[i].severity>queue[highestIndex].severity || queue[i].severity==queue[highestIndex].severity && queue[i].arrivalOrder<queue[highestIndex].arrivalOrder) {
                highestIndex=i;
            }
        }
        Patient treated=queue[highestIndex];
         System.out.println("Treating: " + treated.id + " (Severity " + treated.severity + ")");
         for (int i=highestIndex; i<size-1; i++) {
            queue[i]=queue[i+1];
         }
        size--;
    }
    void displayQueue() {
        if (size == 0) {
            System.out.println("No patients in queue.");
            return;
        }
        System.out.println("Current Queue:");
        for (int i = 0; i < size; i++) {
            System.out.println(queue[i].id + " (Severity " + queue[i].severity + ")");
        }
    }
}
