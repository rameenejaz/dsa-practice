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

}
