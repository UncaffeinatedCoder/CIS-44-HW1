import java.util.Comparator;

public class SimpleSorters {


    public static <K> void bubbleSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            // Inner loop: bubble largest unsorted element to position n-1-i
            for (int j = 0; j < n - 1 - i; j++) {
                if (comp.compare(S[j], S[j + 1]) > 0) {
                    // Swap S[j] and S[j+1]
                    K temp = S[j];
                    S[j] = S[j + 1];
                    S[j + 1] = temp;
                    swapped = true;
                }
            }

            // Early termination: no swaps means array is sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static <K> void insertionSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        for (int i = 1; i < n; i++) {
            K cur = S[i];  // Element to be inserted into sorted portion
            int j = i - 1;

            // Shift elements greater than cur one position to the right
            while (j >= 0 && comp.compare(S[j], cur) > 0) {
                S[j + 1] = S[j];
                j--;
            }

            // Insert cur into its correct position
            S[j + 1] = cur;
        }
    }
}
