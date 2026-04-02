// Helper class for the BST
class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class RecursiveSearch {

    /**
     * Lab 2a: Recursive Binary Search (Helper Method)
     * @param arr The sorted array.
     * @param key The value to find.
     * @param low The starting index of the subarray.
     * @param high The ending index of the subarray.
     * @return The index of the key if found, otherwise -1.
     */
    public static int recursiveBinarySearch(int[] arr, int key, int low, int high) {
        
        // Base Case: If the search range is invalid, the key is not present.
        if (low > high) {
            return -1;
        }

        // 1. Calculate mid index
        int mid = (low + high) / 2;
        
        // 2. Check if arr[mid] is the key (found base case)
        if (arr[mid] == key) {
            return mid;
        }
        
        // 3. If key < arr[mid], recursive call on left half
        if (key < arr[mid]) {
            return recursiveBinarySearch(arr, key, low, mid - 1);
        }
        
        // 4. If key > arr[mid], recursive call on right half
        return recursiveBinarySearch(arr, key, mid + 1, high);
    }

    // Public wrapper method to start the recursive binary search
    public static int searchArray(int[] arr, int key) {
        return recursiveBinarySearch(arr, key, 0, arr.length - 1);
    }

    /**
     * Lab 2b: Binary Search Tree Search
     * @param root The root node of the BST (or subtree).
     * @param key The value to find.
     * @return The Node containing the key, or null if not found.
     */
    public Node searchBST(Node root, int key) {
        
        // Base Case 1: The tree is empty or the key is at the root.
        if (root == null || root.key == key) {
            return root;
        }

        // 1. If key < root's key, search left subtree
        if (key < root.key) {
            return searchBST(root.left, key);
        }
        
        // 2. If key > root's key, search right subtree
        return searchBST(root.right, key);
    }

    // --- Main method for testing ---
    public static void main(String[] args) {
        System.out.println("--- Lab 2: Recursive Search Algorithms ---");
        
        // Test Recursive Binary Search
        int[] sortedData = {10, 20, 30, 40, 50, 60};
        System.out.println("Recursive Binary Search:");
        System.out.println("Find 40: Index " + searchArray(sortedData, 40)); // Expected: 3
        System.out.println("Find 15: Index " + searchArray(sortedData, 15)); // Expected: -1

        // Build a sample BST
        //        40
        //       /  \
        //      20   60
        //     / \   /
        //    10 30 50
        RecursiveSearch bstSearcher = new RecursiveSearch();
        Node root = new Node(40);
        root.left = new Node(20);
        root.right = new Node(60);
        root.left.left = new Node(10);
        root.left.right = new Node(30);
        root.right.left = new Node(50);

        System.out.println("\nBinary Search Tree Search:");
        Node result1 = bstSearcher.searchBST(root, 30);
        System.out.println("Find 30: " + (result1 != null ? "Found (" + result1.key + ")" : "Not Found")); // Expected: Found
        
        Node result2 = bstSearcher.searchBST(root, 45);
        System.out.println("Find 45: " + (result2 != null ? "Found" : "Not Found")); // Expected: Not Found
    }
}
