public class DisasterReliefQueue {
    Region [] regions;
    int size;
    DisasterReliefQueue(int capacity) {
        regions=new Region[capacity];
        size=0;
    }
    void addRegion(String id, double urgency, int population, int accessibility) {
        regions[size++]=new Region(id, urgency, population, accessibility);
    }
    void simulateAllocation(int totalRounds) {
        for (int rounds=1; rounds<=totalRounds; rounds++) {
            int highestIndex = findHighestPriorityIndex();
            if(highestIndex==-1) {
                System.out.println("All urgency levels below threshold. Stopping.");
                break;
            }
            Region r=regions[highestIndex];
            System.out.printf("Cycle %d: Allocating to %s (Priority %.2f)%n", rounds, r.id, r.getPriority());
            r.urgency *= 0.9;
        }
         System.out.print("Final queue state: [");
        for (int i = 0; i < size; i++) {
            System.out.print(regions[i].id);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    int findHighestPriorityIndex() {
        int index = -1;
        double maxPriority = -1;
        for (int i = 0; i < size; i++) {
            if (regions[i].urgency < 20) continue;
            double p = regions[i].getPriority();
            if (p > maxPriority) {
                maxPriority = p;
                index = i;
            }
        }
        return index;
    }
}
