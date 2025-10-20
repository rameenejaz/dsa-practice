import java.util.ArrayList;
public class PriorityQueue {
    private ArrayList<Patient2> patients;
    public PriorityQueue() {
        patients=new ArrayList<>();
    }
    public void addPatient(int id, int severity) {
        patients.add(new Patient2(id, severity));
    }
    public Patient2 treatNextPatient() {
        if (patients.isEmpty()) {
            System.out.println("No patients waiting");
            return null;
        }
        int maxIndex=0;
        for (int i=1; i<patients.size(); i++) {
            if (patients.get(i).severity>patients.get(maxIndex).severity) {
                maxIndex=i;
            }
        }
        return patients.remove(maxIndex);
    }
    public void printQueue() {
        if (patients.isEmpty()) {
            System.out.println("No patients");
            return;
        }
        for (Patient2 p: patients) {
            System.out.println("ID: " +p.id + " Severity: " + p.severity);
        }
    }
    public static void main(String[] args) {
        PriorityQueue er=new PriorityQueue();
        er.addPatient(160, 5);
        er.addPatient(130, 9);
        er.addPatient(025, 5);
        er.printQueue();
        while (true) {
            Patient2 next=er.treatNextPatient();
            if (next==null) 
                break;
            System.out.println("Treating patient ID: " + next.id + " with severity: " + next.severity);
   
        }
    }

}
