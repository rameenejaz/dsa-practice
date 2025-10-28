import java.util.ArrayList;
public class priorityQueue3 {
    private ArrayList <PrinterScheduler> printer;
    priorityQueue3() {
        printer=new ArrayList<>();
    }
    public void addJob(String jobID, int priority, int pages) {
        printer.add(new PrinterScheduler(jobID, priority, pages));
    }
    public PrinterScheduler nextJob() {
        if (printer.isEmpty()) {
            System.out.println("No print job in line");
            return null;
            
        }
        else {
            int maxIndex=0;
            for(int i=1; i<printer.size(); i++) {
                if (printer.get(i).priority> printer.get(maxIndex).priority) {
                    maxIndex=i;
                }
            }
            return printer.remove(maxIndex);
        }
    }

}
