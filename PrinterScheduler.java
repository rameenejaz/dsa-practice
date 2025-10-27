public class PrinterScheduler {
    protected String jobID; 
    protected int priority; 
    protected int pages; 
    protected int submissionOrder; 
    PrinterScheduler(String jobID, int priority, int pages) {
        this.jobID=jobID; 
        this.priority=priority; 
        this.pages=pages; 
    }
}
