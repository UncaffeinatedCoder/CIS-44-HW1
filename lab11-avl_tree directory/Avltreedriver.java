public class AVLTreeDriver {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("=== AVL Tree Implementation Test ===\n");

        // Insert sequence that triggers all 4 rotation types:
        // 10, 20, 30 -> Left rotation (Right-Right case)
        // 5, 4 -> Right rotation (Left-Left case)  
        // 8 -> Left-Right rotation
        // 25 -> Right-Left rotation

        System.out.println("Inserting 10:");
        tree.insert(10);

        System.out.println("Inserting 20:");
        tree.insert(20);

        System.out.println("Inserting 30 (triggers Left rotation at 10):");
        tree.insert(30);
        // Tree becomes:  20
        //               /  \
        //              10  30

        System.out.println("Inserting 5:");
        tree.insert(5);

        System.out.println("Inserting 4 (triggers Right rotation at 10):");
        tree.insert(4);
        // Left subtree becomes:  5
        //                       / \
        //                      4  10

        System.out.println("Inserting 8 (triggers Left-Right rotation at 5):");
        tree.insert(8);
        // Left subtree rebalances

        System.out.println("Inserting 25 (triggers Right-Left rotation at 30):");
        tree.insert(25);
        // Right subtree rebalances

        // Final tree structure:
        //          20
        //        /    \
        //       5      25
        //      / \    /  \
        //     4   8  (varies based on rotations)

        System.out.println("\n=== Final Tree Traversals ===");
        
        System.out.print("Inorder (sorted):   ");
        tree.inorder();
        // Expected: 4 5 8 10 20 25 30

        System.out.print("Preorder (root first): ");
        tree.preorder();

        System.out.print("Postorder (root last): ");
        tree.postorder();

        System.out.println("\n=== Expected Results ===");
        System.out.println("Inorder should be:  4 5 8 10 20 25 30");
        System.out.println("(Preorder/Postorder depend on final tree shape after rotations)");
    }
}
