import java.util.Scanner;
public class QueueProblems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---------- Problem 1 ----------
        System.out.println("=== Problem 1: Circular Queue ===");
        System.out.print("Enter capacity of circular queue: ");
        int cap = sc.nextInt();
        CircularQueue cq = new CircularQueue(cap);

        while (true) {
            System.out.println("\n1. Enqueue  2. Dequeue  3. Display  0. Exit");
            int choice = sc.nextInt();
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    System.out.print("Enter value to enqueue: ");
                    cq.enqueue(sc.nextInt());
                    break;
                case 2:
                    cq.dequeue();
                    break;
                case 3:
                    cq.display();
                    break;
            }
        }

        // ---------- Problem 2 ----------
        System.out.println("\n=== Problem 2: Emergency Room Queue ===");
        System.out.print("Enter max number of patients: ");
        int maxPatients = sc.nextInt();
        EmergencyRoomQueue er = new EmergencyRoomQueue(maxPatients);

        while (true) {
            System.out.println("\n1. Add Patient  2. Treat Next  3. Show Queue  0. Exit");
            int choice = sc.nextInt();
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String id = sc.next();
                    System.out.print("Enter Severity (1-10): ");
                    int severity = sc.nextInt();
                    er.addPatient(id, severity);
                    break;
                case 2:
                    er.treatNextPatient();
                    break;
                case 3:
                    er.displayQueue();
                    break;
            }
        }

        // ---------- Problem 3 ----------
        // System.out.println("\n=== Problem 3: Disaster Relief Simulation ===");
        // System.out.print("Enter number of regions: ");
        // int n = sc.nextInt();
        // DisasterReliefQueue dr = new DisasterReliefQueue(n);

        // for (int i = 0; i < n; i++) {
        //     System.out.println("Enter details for region " + (i + 1) + ":");
        //     System.out.print("Region ID: ");
        //     String rid = sc.next();
        //     System.out.print("Urgency Level (1-100): ");
        //     double urgency = sc.nextDouble();
        //     System.out.print("Population: ");
        //     int pop = sc.nextInt();
        //     System.out.print("Accessibility Index (1-100): ");
        //     int acc = sc.nextInt();
        //     dr.addRegion(rid, urgency, pop, acc);
        // }

        // System.out.print("Enter total number of resource allocation rounds: ");
        // int rounds = sc.nextInt();
        // dr.simulateAllocation(rounds);

        // sc.close();
    }
}