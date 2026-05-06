public class Patient implements Comparable<Patient> {

    private String name;
    private String condition;
    private int priorityScore;    // 1 (low) to 10 (critical)
    private long arrivalTime;     // System.currentTimeMillis() at admission

    public Patient(String name, String condition, int priorityScore) {
        if (priorityScore < 1 || priorityScore > 10) {
            throw new IllegalArgumentException("Priority must be between 1 and 10.");
        }
        this.name = name;
        this.condition = condition;
        this.priorityScore = priorityScore;
        this.arrivalTime = System.currentTimeMillis();
    }

    // --- Getters ---
    public String getName()        { return name; }
    public String getCondition()   { return condition; }
    public int getPriorityScore()  { return priorityScore; }
    public long getArrivalTime()   { return arrivalTime; }

    /**
     * Compares two patients by priority.
     * Returns negative if THIS patient has LOWER priority (should come later).
     * Used by the heap to determine ordering.
     */
    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.priorityScore, other.priorityScore);
    }

    @Override
    public String toString() {
        return String.format("[Priority %2d] %-20s — %s", priorityScore, name, condition);
    }
}
