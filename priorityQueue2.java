import java.util.ArrayList;
public class priorityQueue2 {
    private ArrayList <ReliefAllocation> region;
    priorityQueue2() {
        region=new ArrayList<>();
    }
    public void addRegion(String regionID, int urgencyLevel, int population, int accessIndex) {
        region.add(new ReliefAllocation(regionID, urgencyLevel, population, accessIndex));
    }
    
    public ReliefAllocation treatNextRegion() {
        if (region.isEmpty()) {
            System.out.println("No region to help");
            return null;
        }
        int maxIndex=0;
        for (int i=1; i<region.size(); i++) {
            if(region.get(i).calcPriority(region.get(i).urgencyLevel,region.get(i).population, region.get(i).accessIndex)>region.get(maxIndex).calcPriority(region.get(maxIndex).urgencyLevel, region.get(maxIndex).population, region.get(maxIndex).accessIndex)) {
                maxIndex=i;
            }
        }
        return region.remove(maxIndex);
    }
    public void printQueue() {
        if (region.isEmpty()) {
            System.out.println("No regions to treat");
            return;
        }
        else {
            for (ReliefAllocation r: region) {
                System.out.println("Region " + r.regionID + ", Urgency Level: " + r.urgencyLevel + " Population: " + r.population + ", Accesibility Index: " + r.accessIndex );

            }
        }

    }
    public static void main(String[] args) {
        priorityQueue2 region=new priorityQueue2();
        region.addRegion("R1", 90, 5000, 70);
        region.addRegion("R2", 70, 8000, 60);
        region.addRegion("R3", 85, 4000, 40);
        region.addRegion("R4", 50, 10000, 90);
        while(true) {
            ReliefAllocation next=region.treatNextRegion();
            if (next!=null) {
                double priority=next.calcPriority(next.urgencyLevel, next.population, next.accessIndex);
                System.out.println("Treating region: " + next.regionID + " (Priority " + priority + ")");
            }
            else {
                break;
            }

        }
    }

}
