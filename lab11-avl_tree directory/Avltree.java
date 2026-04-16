class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int key) {
        this.key = key;
        this.height = 1;  // New node starts at height 1
        this.left = null;
        this.right = null;
    }
}

public class AVLTree {

    AVLNode root;

    // Return height of a node (null-safe)
    int height(AVLNode N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    // Return maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Compute balance factor: left height - right height
    int getBalance(AVLNode N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    // Right rotation (for Left-Left case)
    //       y                x
    //      / \              / \
    //     x   T3   -->     T1  y
    //    / \                  / \
    //   T1  T2               T2  T3
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights (y first since it's now lower)
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;  // New root of subtree
    }

    // Left rotation (for Right-Right case)
    //     x                  y
    //    / \                / \
    //   T1  y     -->      x   T3
    //      / \            / \
    //     T2  T3         T1  T2
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights (x first since it's now lower)
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;  // New root of subtree
    }

    // Left-Right rotation (for Left-Right case)
    // Left rotate left child, then right rotate node
    AVLNode leftRightRotate(AVLNode z) {
        z.left = leftRotate(z.left);
        return rightRotate(z);
    }

    // Right-Left rotation (for Right-Left case)
    // Right rotate right child, then left rotate node
    AVLNode rightLeftRotate(AVLNode z) {
        z.right = rightRotate(z.right);
        return leftRotate(z);
    }

    // Public insert method
    public void insert(int key) {
        root = insert(root, key);
    }

    // Recursive insertion with rebalancing
    private AVLNode insert(AVLNode node, int key) {
        // 1. Standard BST insertion
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys not allowed
            return node;
        }

        // 2. Update height of this ancestor node
        node.height = 1 + max(height(node.left), height(node.right));

        // 3. Get balance factor to check if unbalanced
        int balance = getBalance(node);

        // 4. Handle the 4 imbalance cases:

        // Left-Left Case (balance > 1 and key went to left of left child)
        if (balance > 1 && key < node.left.key) {
            System.out.println("Right rotation at node " + node.key);
            return rightRotate(node);
        }

        // Right-Right Case (balance < -1 and key went to right of right child)
        if (balance < -1 && key > node.right.key) {
            System.out.println("Left rotation at node " + node.key);
            return leftRotate(node);
        }

        // Left-Right Case (balance > 1 and key went to right of left child)
        if (balance > 1 && key > node.left.key) {
            System.out.println("Left-Right rotation at node " + node.key);
            return leftRightRotate(node);
        }

        // Right-Left Case (balance < -1 and key went to left of right child)
        if (balance < -1 && key < node.right.key) {
            System.out.println("Right-Left rotation at node " + node.key);
            return rightLeftRotate(node);
        }

        // Node is balanced, return unchanged
        return node;
    }

    // Inorder traversal (Left, Root, Right) - gives sorted order
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // Preorder traversal (Root, Left, Right)
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    // Postorder traversal (Left, Right, Root)
    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(AVLNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.key + " ");
        }
    }
}
