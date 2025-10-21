public class PrinterScheduler {
    private String jobID;
    private int priority;
    private int pages;
    private int submissionOrder;
    PrinterScheduler(String jobID, int priority, int pages) {
        this.jobID=jobID;
        this.priority=priority;
        this.pages=pages;
    }
    
}
