import java.util.ArrayList;
 
// ─── Patient (Entry) ──────────────────────────────────────────────────────────
class Patient implements Comparable<Patient> {
    private String name;
    private String condition;
    private int priorityScore;   // 1 (low) to 10 (critical)
    private long arrivalTime;
 
    public Patient(String name, String condition, int priorityScore) {
        if (priorityScore < 1 || priorityScore > 10)
            throw new IllegalArgumentException("Priority must be 1-10.");
        this.name          = name;
        this.condition     = condition;
        this.priorityScore = priorityScore;
        this.arrivalTime   = System.currentTimeMillis();
    }
 
    public String getName()       { return name; }
    public String getCondition()  { return condition; }
    public int getPriorityScore() { return priorityScore; }
    public long getArrivalTime()  { return arrivalTime; }
 
    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.priorityScore, other.priorityScore);
    }
 
    @Override
    public String toString() {
        return String.format("[Priority %2d] %-20s — %s", priorityScore, name, condition);
    }
}
 
// ─── EmergencyRoomScheduler (Max-Heap) ────────────────────────────────────────
class EmergencyRoomScheduler {
    private ArrayList<Patient> heap = new ArrayList<>();
 
    // Index helpers
    private int parent(int j) { return (j - 1) / 2; }
    private int left(int j)   { return 2 * j + 1; }
    private int right(int j)  { return 2 * j + 2; }
    private boolean hasLeft(int j)  { return left(j)  < heap.size(); }
    private boolean hasRight(int j) { return right(j) < heap.size(); }
 
    private void swap(int i, int j) {
        Patient tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }
 
    // ── admit() — insert + upheap — O(log n) ────────────────────────────────
    public void admit(Patient p) {
        if (p == null) throw new IllegalArgumentException("Patient cannot be null.");
        heap.add(p);
        upheap(heap.size() - 1);
    }
 
    // ── poll() — removeMax + downheap — O(log n) ────────────────────────────
    // Core Phase 3 method: always removes the HIGHEST priority patient.
    public Patient poll() {
        if (isEmpty()) return null;
 
        Patient top  = heap.get(0);                      // save the max (root)
        Patient last = heap.remove(heap.size() - 1);     // pull last element
 
        if (!heap.isEmpty()) {
            heap.set(0, last);   // move last to root
            downheap(0);         // restore heap-order downward
        }
 
        return top;
    }
 
    // ── peek() — O(1) ────────────────────────────────────────────────────────
    public Patient peek() {
        return isEmpty() ? null : heap.get(0);
    }
 
    // ── upheap ───────────────────────────────────────────────────────────────
    private void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (heap.get(j).compareTo(heap.get(p)) > 0) {
                swap(j, p);
                j = p;
            } else break;
        }
    }
 
    // ── downheap ─────────────────────────────────────────────────────────────
    private void downheap(int j) {
        while (hasLeft(j)) {
            int larger = left(j);
            if (hasRight(j) && heap.get(right(j)).compareTo(heap.get(left(j))) > 0)
                larger = right(j);
            if (heap.get(j).compareTo(heap.get(larger)) >= 0) break;
            swap(j, larger);
            j = larger;
        }
    }
 
    public boolean isEmpty() { return heap.isEmpty(); }
    public int size()        { return heap.size(); }
 
    public void printQueue() {
        System.out.println("  Queue (" + size() + " patients):");
        for (Patient p : heap)
            System.out.println("    " + p);
    }
}
 
// ─── Main ─────────────────────────────────────────────────────────────────────
public class SmartScheduler {
    public static void main(String[] args) {
        EmergencyRoomScheduler er = new EmergencyRoomScheduler();
 
        System.out.println("=== Smart Scheduler — Phase 3 Demo ===\n");
 
        // ── Admitting patients ───────────────────────────────────────────────
        System.out.println("--- Admitting Patients ---");
        er.admit(new Patient("Bob",   "Sprained Ankle",   2));
        er.admit(new Patient("Alice", "Cardiac Arrest",  10));
        er.admit(new Patient("Carol", "Broken Arm",       5));
        er.admit(new Patient("Dave",  "Severe Bleeding",  8));
        er.admit(new Patient("Eve",   "Mild Fever",       1));
        er.admit(new Patient("Frank", "Chest Pain",       9));
 
        System.out.println("\nQueue after admissions:");
        er.printQueue();
 
        // ── peek() — who's next without removing ────────────────────────────
        System.out.println("\n--- peek() ---");
        System.out.println("Next up: " + er.peek());
 
        // ── poll() — treat patients in priority order ────────────────────────
        System.out.println("\n--- Treating Patients via poll() ---");
        while (!er.isEmpty()) {
            Patient treated = er.poll();
            System.out.println("Treating: " + treated);
        }
 
        // ── Edge case: poll on empty ─────────────────────────────────────────
        System.out.println("\n--- Edge Case: poll() on empty queue ---");
        System.out.println("Result: " + er.poll());   // null
 
        // ── Mixed admits and polls ───────────────────────────────────────────
        System.out.println("\n--- Mixed Admits and Polls ---");
        er.admit(new Patient("Grace", "Allergic Reaction", 7));
        er.admit(new Patient("Hank",  "Broken Leg",        4));
        System.out.println("Admitted Grace (7) and Hank (4)");
        System.out.println("poll() -> " + er.poll());   // Grace (7)
 
        er.admit(new Patient("Iris", "Heart Attack", 10));
        System.out.println("Admitted Iris (10)");
        System.out.println("poll() -> " + er.poll());   // Iris (10) — jumps the queue
        System.out.println("poll() -> " + er.poll());   // Hank (4)
    }
}
 
