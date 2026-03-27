public class ExpressionTreeDriver {
    public static void main(String[] args) {
      
        
        // Create root (multiplication operator)
        BinaryTreeNode root = new BinaryTreeNode("*");
        
        // Create operator nodes
        BinaryTreeNode nodePlus = new BinaryTreeNode("+");
        BinaryTreeNode nodeMinus = new BinaryTreeNode("-");
        
        // Connect operators to root
        root.left = nodePlus;
        root.right = nodeMinus;
        nodePlus.parent = root;
        nodeMinus.parent = root;

        // Create leaf nodes (operands)
        BinaryTreeNode node3 = new BinaryTreeNode("3");
        BinaryTreeNode node7 = new BinaryTreeNode("7");
        BinaryTreeNode node9 = new BinaryTreeNode("9");
        BinaryTreeNode node4 = new BinaryTreeNode("4");
        
        // Connect leaves to nodePlus (3 + 7)
        nodePlus.left = node3;
        nodePlus.right = node7;
        node3.parent = nodePlus;
        node7.parent = nodePlus;
        
        // Connect leaves to nodeMinus (9 - 4)
        nodeMinus.left = node9;
        nodeMinus.right = node4;
        node9.parent = nodeMinus;
        node4.parent = nodeMinus;

        // 2. Perform Traversals
        System.out.println("--- Preorder Traversal (Prefix Notation) ---");
        root.traversePreorder();
        System.out.println("\nReads as: * + 3 7 - 9 4");
        System.out.println("Meaning: Multiply (3 plus 7) by (9 minus 4)");

        System.out.println("\n--- Inorder Traversal (Infix Notation) ---");
        root.traverseInorder();
        System.out.println("\nReads as: ((3 + 7) * (9 - 4))");
        System.out.println("This is how we normally write math expressions");

        System.out.println("\n--- Postorder Traversal (Postfix/RPN Notation) ---");
        root.traversePostorder();
        System.out.println("\nReads as: 3 7 + 9 4 - *");
        System.out.println("Used by calculators and stack-based evaluation");
        
        // Bonus: Show the actual calculation
        System.out.println("\n--- Expression Evaluation ---");
        System.out.println("(3 + 7) = 10");
        System.out.println("(9 - 4) = 5");
        System.out.println("10 * 5 = 50");
        System.out.println("Final Result: 50");
    }
}
