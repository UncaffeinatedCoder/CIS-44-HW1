import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TwoFourNode {
    List<Integer> keys;
    List<TwoFourNode> children;
    TwoFourNode parent;

    public TwoFourNode() {
        keys = new ArrayList<>();
        children = new ArrayList<>();
        parent = null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    // Check if node is full (3 keys = 4 children capacity)
    public boolean isFull() {
        return keys.size() == 3;
    }

    // Check if node is overflowing (4 keys = needs split)
    public boolean isOverflow() {
        return keys.size() > 3;
    }

    // Find correct child to descend for a given key
    public TwoFourNode getNextChild(int key) {
        int i = 0;
        while (i < keys.size() && key > keys.get(i)) {
            i++;
        }
        return children.get(i);
    }

    // Insert a key into this node (maintains sorted order)
    public void insertKey(int key) {
        keys.add(key);
        Collections.sort(keys);
    }

    // Insert a child at the correct position based on its first key
    public void insertChild(TwoFourNode child) {
        if (child.keys.isEmpty()) {
            children.add(child);
            return;
        }
        
        int childKey = child.keys.get(0);
        int i = 0;
        while (i < children.size() && !children.get(i).keys.isEmpty() 
               && children.get(i).keys.get(0) < childKey) {
            i++;
        }
        children.add(i, child);
        child.parent = this;
    }
}

public class TwoFourTree {

    private TwoFourNode root;

    public TwoFourTree() {
        root = new TwoFourNode();
    }

    public void insert(int key) {
        TwoFourNode node = root;

        // 1. Descend to the leaf node
        while (!node.isLeaf()) {
            node = node.getNextChild(key);
        }

        // 2. Insert key in leaf
        node.insertKey(key);

        // 3. Handle overflow by splitting (may propagate up)
        while (node != null && node.isOverflow()) {
            split(node);
            node = node.parent;
        }
    }

    private void split(TwoFourNode node) {
        System.out.println("  Splitting node with keys: " + node.keys);

        // Node has 4 keys: [0, 1, 2, 3]
        // Key at index 1 (second key) goes up to parent
        // Keys [0] stay in left node, keys [2, 3] go to right node
        
        int promoteKey = node.keys.get(1);  // Middle-left key promotes
        
        // Create left node with first key
        TwoFourNode leftNode = new TwoFourNode();
        leftNode.keys.add(node.keys.get(0));
        
        // Create right node with last two keys
        TwoFourNode rightNode = new TwoFourNode();
        rightNode.keys.add(node.keys.get(2));
        rightNode.keys.add(node.keys.get(3));

        // If not a leaf, distribute children
        if (!node.isLeaf()) {
            // Left node gets first 2 children
            leftNode.children.add(node.children.get(0));
            leftNode.children.add(node.children.get(1));
            node.children.get(0).parent = leftNode;
            node.children.get(1).parent = leftNode;

            // Right node gets last 3 children
            rightNode.children.add(node.children.get(2));
            rightNode.children.add(node.children.get(3));
            rightNode.children.add(node.children.get(4));
            node.children.get(2).parent = rightNode;
            node.children.get(3).parent = rightNode;
            node.children.get(4).parent = rightNode;
        }

        // Handle parent
        if (node.parent == null) {
            // Splitting root - create new root
            TwoFourNode newRoot = new TwoFourNode();
            newRoot.keys.add(promoteKey);
            newRoot.children.add(leftNode);
            newRoot.children.add(rightNode);
            leftNode.parent = newRoot;
            rightNode.parent = newRoot;
            root = newRoot;
            System.out.println("  New root created with key: " + promoteKey);
        } else {
            // Insert promoted key into parent
            TwoFourNode parent = node.parent;
            
            // Remove the old node from parent's children
            parent.children.remove(node);
            
            // Add the promoted key to parent
            parent.insertKey(promoteKey);
            
            // Add new children to parent
            parent.insertChild(leftNode);
            parent.insertChild(rightNode);
            
            System.out.println("  Promoted key " + promoteKey + " to parent");
        }
    }

    // Inorder traversal - prints keys in sorted order
    public void inorder() {
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();
    }

    private void inorder(TwoFourNode node) {
        if (node == null) return;

        if (node.isLeaf()) {
            // Leaf node - just print all keys
            for (int key : node.keys) {
                System.out.print(key + " ");
            }
        } else {
            // Internal node - interleave children and keys
            int i;
            for (i = 0; i < node.keys.size(); i++) {
                if (i < node.children.size()) {
                    inorder(node.children.get(i));
                }
                System.out.print(node.keys.get(i) + " ");
            }
            // Print last child (there's always one more child than keys)
            if (i < node.children.size()) {
                inorder(node.children.get(i));
            }
        }
    }

    // Preorder traversal
    public void preorder() {
        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();
    }

    private void preorder(TwoFourNode node) {
        if (node == null) return;

        // Print this node's keys first
        for (int key : node.keys) {
            System.out.print(key + " ");
        }

        // Then visit children
        for (TwoFourNode child : node.children) {
            preorder(child);
        }
    }

    // Print tree structure for debugging
    public void printStructure() {
        System.out.println("Tree Structure:");
        printStructure(root, 0);
    }

    private void printStructure(TwoFourNode node, int level) {
        if (node == null) return;
        
        String indent = "  ".repeat(level);
        System.out.println(indent + "Node: " + node.keys + (node.isLeaf() ? " (leaf)" : ""));
        
        for (TwoFourNode child : node.children) {
            printStructure(child, level + 1);
        }
    }
}
