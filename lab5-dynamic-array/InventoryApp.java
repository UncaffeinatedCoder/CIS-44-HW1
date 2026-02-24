public class InventoryApp {
    public static void main(String[] args) {

        Inventory inv = new Inventory();

        // --- Add initial items ---
        inv.addItem(new Item("Wooden Wand"));
        inv.addItem(new Item("Dragon Scale"));
        inv.addItem(new Item("Health Potion"));
        inv.addItem(new Item("Iron Shield"));

        System.out.println("=== Inventory BEFORE combining ===");
        inv.display();

        System.out.println("\n>>> Attempting to combine 'Wooden Wand' and 'Dragon Scale'...");
        inv.combineItems("Wooden Wand", "Dragon Scale");

        System.out.println("\n=== Inventory AFTER combining ===");
        inv.display();

        // --- Edge case: item not found ---
        System.out.println("\n>>> Attempting to combine 'Wooden Wand' and 'Iron Shield'...");
        inv.combineItems("Wooden Wand", "Iron Shield");

        System.out.println("\n=== Final Inventory ===");
        inv.display();
    }
}

