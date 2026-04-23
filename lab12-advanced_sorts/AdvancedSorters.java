import java.util.Arrays;
import java.util.Comparator;

public class AdvancedSorters {


    public static <K> void mergeSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        if (n < 2) return;  // Base case: 0 or 1 element is already sorted

        // Divide: split into two halves
        int mid = n / 2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);
        K[] S2 = Arrays.copyOfRange(S, mid, n);

        // Conquer: recursively sort each half
        mergeSort(S1, comp);
        mergeSort(S2, comp);

        // Combine: merge sorted halves back into S
        merge(S, S1, S2, comp);
    }

    /**
     * Merges two sorted arrays S1 and S2 back into S.
     */
    private static <K> void merge(K[] S, K[] S1, K[] S2, Comparator<K> comp) {
        int i = 0, j = 0, k = 0;

        // Compare front elements of each half, take the smaller
        while (i < S1.length && j < S2.length) {
            if (comp.compare(S1[i], S2[j]) <= 0) {
                S[k++] = S1[i++];
            } else {
                S[k++] = S2[j++];
            }
        }

        // Copy any remaining elements from S1
        while (i < S1.length) {
            S[k++] = S1[i++];
        }

        // Copy any remaining elements from S2
        while (j < S2.length) {
            S[k++] = S2[j++];
        }
    }

    // ─── Quick Sort ──────────────────────────────────────────────────────────

    public static <K> void quickSort(K[] S, Comparator<K> comp) {
        quickSort(S, comp, 0, S.length - 1);
    }

    /**
     * Private recursive helper for Quick Sort.
     */
    private static <K> void quickSort(K[] S, Comparator<K> comp, int a, int b) {
        if (a >= b) return;  // Base case: 0 or 1 element

        // Divide: partition around pivot, get pivot's final index
        int pivotIndex = partition(S, comp, a, b);

        // Conquer: sort left and right of pivot
        quickSort(S, comp, a, pivotIndex - 1);
        quickSort(S, comp, pivotIndex + 1, b);
    }


    private static <K> int partition(K[] S, Comparator<K> comp, int a, int b) {
        K pivot = S[b];  // Choose last element as pivot
        int i = a - 1;   // i marks the boundary of elements <= pivot

        for (int j = a; j < b; j++) {
            // If current element is <= pivot, move it to left side
            if (comp.compare(S[j], pivot) <= 0) {
                i++;
                // Swap S[i] and S[j]
                K temp = S[i];
                S[i] = S[j];
                S[j] = temp;
            }
        }

        // Place pivot in its final sorted position
        K temp = S[i + 1];
        S[i + 1] = S[b];
        S[b] = temp;

        return i + 1;  // Return pivot's final index
    }
}
