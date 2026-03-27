public class BinaryTreeNode {
    String value; // Can be an operator "+" or an operand "3"
    BinaryTreeNode parent;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(String value) {
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    /**
     * Performs a preorder traversal starting from this node.
     * (Visit Parent, then Left, then Right)
     * This outputs Prefix notation: * + 3 7 - 9 4
     */
    public void traversePreorder() {
        // 1. Print this node's value
        System.out.print(this.value + " ");
        
        // 2. Recursively call on left child (if not null)
        if (left != null) {
            left.traversePreorder();
        }
        
        // 3. Recursively call on right child (if not null)
        if (right != null) {
            right.traversePreorder();
        }
    }

    /**
     * Performs an inorder traversal starting from this node.
     * (Visit Left, then Parent, then Right)
     * This outputs Infix notation with parentheses: ((3 + 7) * (9 - 4))
     */
    public void traverseInorder() {
        // Check if this is an operator (has children) - add parentheses
        boolean isOperator = (left != null || right != null);
        
        if (isOperator) {
            System.out.print("( ");
        }
        
        // 1. Recursively call on left child (if not null)
        if (left != null) {
            left.traverseInorder();
        }
        
        // 2. Print this node's value
        System.out.print(this.value + " ");
        
        // 3. Recursively call on right child (if not null)
        if (right != null) {
            right.traverseInorder();
        }
        
        if (isOperator) {
            System.out.print(") ");
        }
    }

    /**
     * Performs a postorder traversal starting from this node.
     * (Visit Left, then Right, then Parent)
     * This outputs Postfix (RPN) notation: 3 7 + 9 4 - *
     */
    public void traversePostorder() {
        // 1. Recursively call on left child (if not null)
        if (left != null) {
            left.traversePostorder();
        }
        
        // 2. Recursively call on right child (if not null)
        if (right != null) {
            right.traversePostorder();
        }
        
        // 3. Print this node's value
        System.out.print(this.value + " ");
    }
}
