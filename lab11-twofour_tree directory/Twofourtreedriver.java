public class TwoFourTreeDriver {
    public static void main(String[] args) {
        TwoFourTree tree = new TwoFourTree();

        System.out.println("=== 2-4 Tree Implementation Test ===\n");

        // Test sequence that forces multiple splits
        int[] keys = {10, 20, 30, 40, 50, 60, 70, 80, 90, 5, 15, 25, 35};

        System.out.println("Inserting keys into 2-4 Tree...\n");
        for (int key : keys) {
            System.out.println("Inserting: " + key);
            tree.insert(key);
        }

        System.out.println("\n=== Final Tree ===");
        tree.printStructure();

        System.out.println("\n=== Traversals ===");
        tree.inorder();
        tree.preorder();

        System.out.println("\nExpected Inorder: 5 10 15 20 25 30 35 40 50 60 70 80 90");
        System.out.println("(All keys in sorted ascending order)");

        // Additional test with smaller sequence
        System.out.println("\n\n=== Second Test: Simpler Sequence ===");
        TwoFourTree tree2 = new TwoFourTree();
        int[] keys2 = {10, 20, 30, 40, 50, 5, 15, 25, 35};
        
        for (int key : keys2) {
            System.out.println("Inserting: " + key);
            tree2.insert(key);
        }
        
        System.out.println("\nFinal Structure:");
        tree2.printStructure();
        tree2.inorder();
    }
}
