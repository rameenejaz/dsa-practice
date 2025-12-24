// hash table implementation for student records with linear probing, dynamic resizing, and user interaction
import java.util.Scanner;

public class StudentHashTable {

    private static class Entry {
        int key;
        String value;
        boolean isDeleted;
        Entry(int key, String value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }

    private Entry[] table;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public StudentHashTable(int initialCapacity) {
        int primeCapacity = nextPrime(initialCapacity);
        table = new Entry[primeCapacity];
        size = 0;
    }

    public StudentHashTable() {
        this(11);
    }

    public void insert(int rNumber, String name) {
        double loadFactor = (double) size / table.length;
        if (loadFactor >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        insertInternal(rNumber, name);
        System.out.println("Inserted Successfully!");
    }

    public String search(int rNumber) {
        int index = hash(rNumber);
        int capacity = table.length;

        for (int i = 0; i < capacity; i++) {
            int probeIndex = (index + i) % capacity;
            Entry entry = table[probeIndex];

            if (entry == null) return "Not Found";
            if (!entry.isDeleted && entry.key == rNumber) {
                return entry.value;
            }
        }
        return "Not Found";
    }

    public void delete(int rNumber) {
        int index = hash(rNumber);
        int capacity = table.length;

        for (int i = 0; i < capacity; i++) {
            int probeIndex = (index + i) % capacity;
            Entry entry = table[probeIndex];
            if (entry == null) return;
            if (!entry.isDeleted && entry.key == rNumber) {
                entry.isDeleted = true;
                size--;
                System.out.println("Record Deleted Successfully!");
                return;
            }
        }
        System.out.println("Record Not Found!");
    }

    private void insertInternal(int rNumber, String name) {
        int index = hash(rNumber);
        int capacity = table.length;
        int firstDeletedIndex = -1;

        for (int i = 0; i < capacity; i++) {
            int probeIndex = (index + i) % capacity;
            Entry entry = table[probeIndex];

            if (entry != null && !entry.isDeleted && entry.key == rNumber) {
                entry.value = name;
                System.out.println("Updated Existing Record!");
                return;
            }

            if (entry != null && entry.isDeleted && firstDeletedIndex == -1) {
                firstDeletedIndex = probeIndex;
            }

            if (entry == null) {
                int target = (firstDeletedIndex != -1) ? firstDeletedIndex : probeIndex;
                table[target] = new Entry(rNumber, name);
                size++;
                return;
            }
        }

        if (firstDeletedIndex != -1) {
            table[firstDeletedIndex] = new Entry(rNumber, name);
            size++;
        }
    }

    private int hash(int key) {
        long sq = (long) key * key;
        long middle4 = (sq / 100) % 10000;
        return (int)(middle4 % table.length);
    }

    private void resize() {
        int oldCap = table.length;
        int newCap = nextPrime(oldCap + oldCap / 2);

        Entry[] oldTable = table;
        table = new Entry[newCap];
        size = 0;

        for (Entry e : oldTable) {
            if (e != null && !e.isDeleted) {
                insertInternal(e.key, e.value);
            }
        }
        System.out.println("Resized to capacity: " + newCap);
    }

    private int nextPrime(int n) {
        while (!isPrime(n)) n++;
        return n;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // ---------------- MAIN - USER INPUT ----------------
    public static void main(String[] args) {
        StudentHashTable table = new StudentHashTable();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Record Management =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Search Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Registration Number: ");
                    int r = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    table.insert(r, name);
                    break;

                case 2:
                    System.out.print("Enter Registration Number: ");
                    int rSearch = sc.nextInt();
                    System.out.println("Result: " + table.search(rSearch));
                    break;

                case 3:
                    System.out.print("Enter Registration Number: ");
                    int rDelete = sc.nextInt();
                    table.delete(rDelete);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }
        }
    }
}
