/**
 * Simulates a printer that manages a queue of print jobs.
 * Uses FIFO (First In, First Out) ordering - jobs are processed
 * in the order they were received.
 */
public class Printer {
    /** Queue to store pending print jobs */
    private Queue<PrintJob> jobQueue;

    /**
     * Constructs a new Printer with an empty job queue.
     */
    public Printer() {
        // Initialize the jobQueue with a LinkedQueue
        jobQueue = new LinkedQueue<>();
    }

    /**
     * Adds a new print job to the rear of the queue.
     * @param job The print job to add
     */
    public void addJob(PrintJob job) {
        System.out.println("Adding to queue: " + job);
        // Enqueue the job at the rear of the queue
        jobQueue.enqueue(job);
    }

    /**
     * Processes the job at the front of the queue.
     * If the queue is empty, displays a message indicating no jobs to process.
     * Otherwise, removes and "prints" the job at the front.
     */
    public void processNextJob() {
        // Check if the queue is empty
        if (jobQueue.isEmpty()) {
            System.out.println("No jobs in queue. Printer is idle.");
        } else {
            // Dequeue the job from the front of the queue
            PrintJob job = jobQueue.dequeue();
            // Simulate printing by displaying a message
            System.out.println("Processing: " + job);
            System.out.println("  -> Printing " + job.getPageCount() + " page(s)... Done!");
        }
    }

    /**
     * Returns the number of jobs currently waiting in the queue.
     * @return the number of pending jobs
     */
    public int getPendingJobCount() {
        return jobQueue.size();
    }

    /**
     * Checks if the printer has any pending jobs.
     * @return true if there are no pending jobs, false otherwise
     */
    public boolean isIdle() {
        return jobQueue.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("      PRINTER QUEUE SIMULATION");
        System.out.println("========================================\n");

        Printer officePrinter = new Printer();

        // Add initial print jobs
        System.out.println("--- Adding Print Jobs ---");
        officePrinter.addJob(new PrintJob("Annual_Report.pdf", 25));
        officePrinter.addJob(new PrintJob("Meeting_Agenda.docx", 2));
        officePrinter.addJob(new PrintJob("Presentation_Slides.pptx", 30));

        System.out.println("\n--- Starting to Print ---");
        officePrinter.processNextJob(); // Should print Annual_Report.pdf
        officePrinter.processNextJob(); // Should print Meeting_Agenda.docx

        System.out.println("\nNew job arrives...");
        officePrinter.addJob(new PrintJob("Urgent_Memo.pdf", 1));

        System.out.println("\n--- Continuing to Print ---");
        officePrinter.processNextJob(); // Should print Presentation_Slides.pptx
        officePrinter.processNextJob(); // Should print Urgent_Memo.pdf
        officePrinter.processNextJob(); // Should say the queue is empty

        // Additional demonstration
        System.out.println("\n========================================");
        System.out.println("      ADDITIONAL TEST CASES");
        System.out.println("========================================\n");

        // Test adding multiple jobs and processing all
        System.out.println("--- Batch Job Test ---");
        officePrinter.addJob(new PrintJob("Invoice_001.pdf", 3));
        officePrinter.addJob(new PrintJob("Invoice_002.pdf", 3));
        officePrinter.addJob(new PrintJob("Invoice_003.pdf", 3));
        
        System.out.println("\nPending jobs: " + officePrinter.getPendingJobCount());
        
        System.out.println("\n--- Processing All Jobs ---");
        while (!officePrinter.isIdle()) {
            officePrinter.processNextJob();
        }

        System.out.println("\nPending jobs: " + officePrinter.getPendingJobCount());
        System.out.println("Printer idle: " + officePrinter.isIdle());

        System.out.println("\n========================================");
        System.out.println("      SIMULATION COMPLETE!");
        System.out.println("========================================");
    }
}
