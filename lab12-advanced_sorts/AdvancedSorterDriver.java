import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class AdvancedSortDriver {
    public static void main(String[] args) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        Random rand = new Random(42);  // Fixed seed for reproducibility

        // --- Test 1: Small random array (easy to verify) ---
        int N_SMALL = 20;
        Integer[] arr1 = new Integer[N_SMALL];
        for (int i = 0; i < N_SMALL; i++) arr1[i] = rand.nextInt(100);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        System.out.println("--- Test 1: Random Array (N=20) ---");
        System.out.println("Original:   " + Arrays.toString(arr1));
        AdvancedSorters.mergeSort(arr1, comp);
        System.out.println("Merge Sort: " + Arrays.toString(arr1));
        AdvancedSorters.quickSort(arr2, comp);
        System.out.println("Quick Sort: " + Arrays.toString(arr2));

        // --- Test 2: Large random array (100+ elements) ---
        int N_LARGE = 100;
        Integer[] big1 = new Integer[N_LARGE];
        for (int i = 0; i < N_LARGE; i++) big1[i] = rand.nextInt(1000);
        Integer[] big2 = Arrays.copyOf(big1, big1.length);

        System.out.println("\n--- Test 2: Large Random Array (N=100) ---");
        AdvancedSorters.mergeSort(big1, comp);
        AdvancedSorters.quickSort(big2, comp);
        System.out.println("Merge Sort first 10: " + Arrays.toString(Arrays.copyOf(big1, 10)));
        System.out.println("Quick Sort first 10: " + Arrays.toString(Arrays.copyOf(big2, 10)));
        System.out.println("Merge Sort last 10:  " + Arrays.toString(Arrays.copyOfRange(big1, 90, 100)));
        System.out.println("Quick Sort last 10:  " + Arrays.toString(Arrays.copyOfRange(big2, 90, 100)));
        System.out.println("Both match: " + Arrays.equals(big1, big2));

        // --- Test 3: Already sorted (best case for merge, tests quick pivot) ---
        Integer[] sorted1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] sorted2 = Arrays.copyOf(sorted1, sorted1.length);

        System.out.println("\n--- Test 3: Already Sorted Array ---");
        System.out.println("Original:   " + Arrays.toString(sorted1));
        AdvancedSorters.mergeSort(sorted1, comp);
        System.out.println("Merge Sort: " + Arrays.toString(sorted1));
        AdvancedSorters.quickSort(sorted2, comp);
        System.out.println("Quick Sort: " + Arrays.toString(sorted2));

        // --- Test 4: Reverse sorted (worst case exposure) ---
        Integer[] rev1 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] rev2 = Arrays.copyOf(rev1, rev1.length);

        System.out.println("\n--- Test 4: Reverse Sorted Array ---");
        System.out.println("Original:   " + Arrays.toString(rev1));
        AdvancedSorters.mergeSort(rev1, comp);
        System.out.println("Merge Sort: " + Arrays.toString(rev1));
        AdvancedSorters.quickSort(rev2, comp);
        System.out.println("Quick Sort: " + Arrays.toString(rev2));

        // --- Test 5: Strings using natural order comparator ---
        String[] words1 = {"mango", "apple", "cherry", "banana", "date", "elderberry"};
        String[] words2 = Arrays.copyOf(words1, words1.length);
        Comparator<String> strComp = Comparator.naturalOrder();

        System.out.println("\n--- Test 5: String Array ---");
        System.out.println("Original:   " + Arrays.toString(words1));
        AdvancedSorters.mergeSort(words1, strComp);
        System.out.println("Merge Sort: " + Arrays.toString(words1));
        AdvancedSorters.quickSort(words2, strComp);
        System.out.println("Quick Sort: " + Arrays.toString(words2));
    }
}
