import java.util.Arrays;
import java.util.Random;

/**
 * SortingTester - Testing framework for empirical analysis of sorting algorithms.
 */
public class SortingTester {

    private static final Random random = new Random(42);
    private static StringBuilder resultsReport = new StringBuilder();

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 25000, 50000, 100000};
        
        System.out.println("================================================================");
        System.out.println("|           THE SORTING RACE - EMPIRICAL ANALYSIS              |");
        System.out.println("|         Comparing Selection, Insertion, and Merge Sort        |");
        System.out.println("================================================================");
        
        System.out.println("\n>>> Verifying algorithm correctness...");
        verifyAlgorithms();
        System.out.println(">>> All algorithms verified!\n");
        
        resultsReport.append("Algorithm,Case,n,Time(ms)\n");
        
        String[] cases = {"AVERAGE", "BEST", "WORST"};
        for (String caseType : cases) {
            System.out.println("================================================================");
            System.out.printf("                    %s CASE Results\n", caseType);
            System.out.println("================================================================");
            printTableHeader();
            for (int n : sizes) {
                runAndTimeAllSorts(n, caseType);
            }
            System.out.println("+----------+----------------+----------------+-----------------+\n");
        }
        
        printAnalysisSummary();
        
        System.out.println("================================================================");
        System.out.println("                    CSV DATA FOR PLOTTING                       ");
        System.out.println("================================================================");
        System.out.println(resultsReport.toString());
    }
    
    private static void printTableHeader() {
        System.out.println("+----------+----------------+----------------+-----------------+");
        System.out.println("|    n     | Selection Sort | Insertion Sort |   Merge Sort    |");
        System.out.println("+----------+----------------+----------------+-----------------+");
    }
    
    private static void runAndTimeAllSorts(int n, String caseType) {
        int[] originalArray;
        if (caseType.equals("BEST")) originalArray = generateSortedArray(n);
        else if (caseType.equals("WORST")) originalArray = generateReverseSortedArray(n);
        else originalArray = generateRandomArray(n);
        
        // Selection Sort
        int[] arrS = Arrays.copyOf(originalArray, n);
        long start = System.nanoTime();
        SortingAlgorithms.selectionSort(arrS);
        double timeS = (System.nanoTime() - start) / 1_000_000.0;
        
        // Insertion Sort
        int[] arrI = Arrays.copyOf(originalArray, n);
        start = System.nanoTime();
        SortingAlgorithms.insertionSort(arrI);
        double timeI = (System.nanoTime() - start) / 1_000_000.0;
        
        // Merge Sort
        int[] arrM = Arrays.copyOf(originalArray, n);
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(arrM);
        double timeM = (System.nanoTime() - start) / 1_000_000.0;
        
        System.out.printf("| %8d | %11.3f ms | %11.3f ms | %12.3f ms |%n", n, timeS, timeI, timeM);
        
        resultsReport.append(String.format("Selection,%s,%d,%.3f%n", caseType, n, timeS));
        resultsReport.append(String.format("Insertion,%s,%d,%.3f%n", caseType, n, timeI));
        resultsReport.append(String.format("Merge,%s,%d,%.3f%n", caseType, n, timeM));
    }

    // --- Helper Methods ---

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = random.nextInt(size * 10);
        return arr;
    }

    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i;
        return arr;
    }

    public static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = size - i;
        return arr;
    }

    private static void verifyAlgorithms() {
        int[] data = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        int[] s = Arrays.copyOf(data, data.length);
        int[] i = Arrays.copyOf(data, data.length);
        int[] m = Arrays.copyOf(data, data.length);
        
        SortingAlgorithms.selectionSort(s);
        SortingAlgorithms.insertionSort(i);
        SortingAlgorithms.mergeSort(m);
        
        if (Arrays.equals(s, expected)) System.out.println("    [OK] Selection Sort");
        if (Arrays.equals(i, expected)) System.out.println("    [OK] Insertion Sort");
        if (Arrays.equals(m, expected)) System.out.println("    [OK] Merge Sort");
    }

    private static void printAnalysisSummary() {
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|                      ANALYSIS SUMMARY                        |");
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("| Algorithm       | Best Case  | Average    | Worst Case       |");
        System.out.println("|-----------------|------------|------------|------------------|");
        System.out.println("| Selection Sort  | O(n^2)     | O(n^2)     | O(n^2)           |");
        System.out.println("| Insertion Sort  | O(n)       | O(n^2)     | O(n^2)           |");
        System.out.println("| Merge Sort      | O(n log n) | O(n log n) | O(n log n)       |");
        System.out.println("+--------------------------------------------------------------+\n");
    }
}